package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class EditFoodCupboardItem extends AppCompatActivity {

    private String currentQuantityRemaining;
    private String currentAmountRemaining;
    private String currentUserInputType;
    private String newUserInputType;
    private String expiryDate;
    private EditText dayExpiryView;
    //MAY NEED TO INITIALISE EARLIER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_cupboard_item);

        FoodCupboardItem originalItem = getIntent().getParcelableExtra("editFCItem");

        String currentName = originalItem.getName();
        final EditText nameView = (EditText) findViewById(R.id.foodCupboardNameEdit);
        nameView.setText(currentName, TextView.BufferType.EDITABLE);

        String currentDayBought = originalItem.getDayBought();
        final EditText dayBoughtView = (EditText) findViewById(R.id.foodCupboardDayBoughtEdit);
        dayBoughtView.setText(currentDayBought, TextView.BufferType.EDITABLE);

        String currentDayExpiry = originalItem.getDayExpiry();
        dayExpiryView = (EditText) findViewById(R.id.foodCupboardDayExpiryEdit);
        dayExpiryView.setText(currentDayExpiry, TextView.BufferType.EDITABLE);

        String currentDaysRemaining = originalItem.getDaysRemaining();
        final EditText daysRemainingView = (EditText) findViewById(R.id.foodCupboardDaysRemainingEdit);
        daysRemainingView.setText(currentDaysRemaining, TextView.BufferType.EDITABLE);

        String currentQuantityBought = originalItem.getQuantityBought();
        final EditText quantityBoughtView = (EditText)findViewById(R.id.foodCupboardQuantityBoughtEdit);
        quantityBoughtView.setText(currentQuantityBought, TextView.BufferType.EDITABLE);

        currentQuantityRemaining = originalItem.getQuantityRemaining();
        final EditText quantityRemainingView = (EditText) findViewById(R.id.foodCupboardQuantityRemainingEdit);
        quantityRemainingView.setText(currentQuantityRemaining, TextView.BufferType.EDITABLE);

        String currentAmountBought = originalItem.getAmountBought();
        final EditText amountBoughtView = (EditText) findViewById(R.id.foodCupboardAmountBoughtEdit);
        amountBoughtView.setText(currentAmountBought, TextView.BufferType.EDITABLE);

        currentAmountRemaining = originalItem.getAmountRemaining();
        final EditText amountRemainingView = (EditText) findViewById(R.id.foodCupboardAmountRemainingEdit);
        amountRemainingView.setText(currentAmountRemaining, TextView.BufferType.EDITABLE);

        RelativeLayout quantityHolder = (RelativeLayout)findViewById(R.id.foodCupboardQuantityHolder);
        RelativeLayout amountHolder = (RelativeLayout)findViewById(R.id.foodCupboardAmountHolder);
        currentUserInputType = originalItem.getUserInputType();

        Button quantityOption = (Button)findViewById(R.id.editFCQuantityOption);
        Button amountOption = (Button)findViewById(R.id.editFCAmountOption);
        Button bothOption = (Button)findViewById(R.id.editFCBothQuantityAndAmountOption);

        TextView hintView = (TextView)findViewById(R.id.editFCHintBox);
        final SeekBar seekbar = (SeekBar) findViewById(R.id.seek_bar);

        newUserInputType = currentUserInputType;

        if (currentUserInputType.equals("QUANTITY"))
        {
            hintView.setText("Hint: Use this option for 5 apples");
            quantityOption.setBackgroundColor(ContextCompat.getColor(EditFoodCupboardItem.this, R.color.colourBlueSelected));
            amountOption.setBackgroundColor(Color.GRAY);
            bothOption.setBackgroundColor(Color.GRAY);
            amountHolder.setVisibility(View.GONE);
            seekbar.setMax(Integer.valueOf(currentQuantityBought));
            seekbar.setMax(Integer.valueOf(currentQuantityRemaining));
        }
        else if (currentUserInputType.equals("AMOUNT"))
        {
            hintView.setText("Hint: Use this option for 1 BOTTLE OF 1000ml of Milk");
            amountOption.setBackgroundColor(ContextCompat.getColor(EditFoodCupboardItem.this, R.color.colourBlueSelected));
            quantityOption.setBackgroundColor(Color.GRAY);
            bothOption.setBackgroundColor(Color.GRAY);
            quantityHolder.setVisibility(View.GONE);
            seekbar.setMax(Integer.valueOf(currentAmountBought));
            seekbar.setProgress(Integer.valueOf(currentAmountRemaining));
        }
        else
        {
            hintView.setText("Hint: Use this option for >1 BOTTLE OF 1000ml of Milk");
            bothOption.setBackgroundColor(ContextCompat.getColor(EditFoodCupboardItem.this, R.color.colourBlueSelected));
            quantityOption.setBackgroundColor(Color.GRAY);
            amountOption.setBackgroundColor(Color.GRAY);
            seekbar.setMax(Integer.valueOf(currentAmountBought));
            seekbar.setProgress(Integer.valueOf(currentAmountRemaining));
        }

        quantityOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                quantityHolder.setVisibility(View.VISIBLE);
                quantityRemainingView.setText(originalItem.getQuantityRemaining(),TextView.BufferType.EDITABLE);

                hintView.setText("Hint: Use this option for 5 apples");
                newUserInputType = "QUANTITY";
                quantityOption.setBackgroundColor(ContextCompat.getColor(EditFoodCupboardItem.this, R.color.colourBlueSelected));
                amountOption.setBackgroundColor(Color.GRAY);
                bothOption.setBackgroundColor(Color.GRAY);
                amountHolder.setVisibility(View.GONE);
                seekbar.setMax(Integer.valueOf(currentQuantityBought));
                seekbar.setProgress(Integer.valueOf(currentQuantityRemaining));
            }
        });


        amountOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                amountHolder.setVisibility(View.VISIBLE);
                amountRemainingView.setText(originalItem.getAmountRemaining(),TextView.BufferType.EDITABLE);

                newUserInputType = "AMOUNT";
                hintView.setText("Hint: Use this option for 1 BOTTLE OF 1000ml of Milk");
                amountOption.setBackgroundColor(ContextCompat.getColor(EditFoodCupboardItem.this, R.color.colourBlueSelected));
                quantityOption.setBackgroundColor(Color.GRAY);
                bothOption.setBackgroundColor(Color.GRAY);
                quantityHolder.setVisibility(View.GONE);
                seekbar.setMax(Integer.valueOf(currentAmountBought));
                seekbar.setProgress(Integer.valueOf(currentAmountRemaining));
            }
        });

        bothOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                quantityHolder.setVisibility(View.VISIBLE);
                amountHolder.setVisibility(View.VISIBLE);
                quantityRemainingView.setText(originalItem.getQuantityRemaining(),TextView.BufferType.EDITABLE);
                amountRemainingView.setText(originalItem.getAmountRemaining(),TextView.BufferType.EDITABLE);

                newUserInputType = "QUANTITY&AMOUNT";
                hintView.setText("Hint: Use option for >1 BOTTLE OF 100ml of Milk");
                bothOption.setBackgroundColor(ContextCompat.getColor(EditFoodCupboardItem.this, R.color.colourBlueSelected));
                quantityOption.setBackgroundColor(Color.GRAY);
                amountOption.setBackgroundColor(Color.GRAY);
                seekbar.setMax(Integer.valueOf(currentAmountBought));
                seekbar.setProgress(Integer.valueOf(currentAmountRemaining));
            }
        });

        ImageView calendarIcon = (ImageView)findViewById(R.id.imageViewCalendar);
        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(EditFoodCupboardItem.this, FCSelectExpiryDate.class);
                startActivityForResult(intent, 1);
            }

        });


        //CODE TO DYNAMICALLY UPDATE THE PROGRESS BAR
        /*amountRemainingView.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s)
            {
                //ADD ERROR CHECKING - including character check!
                currentAmountRemaining = s.toString();
                int textLength = currentAmountRemaining.length();
                amountRemainingView.setSelection(textLength, textLength);

                String currentValue = s.toString();

                if (currentValue.isEmpty())
                {
                    currentAmountRemaining = String.valueOf(0);
                }

                if (containsCharacters(currentValue))
                {
                    if (currentUserInputType.equals("AMOUNT") | currentUserInputType.equals("QUANTITY&AMOUNT"))
                    seekbar.setProgress(Integer.valueOf(currentAmountRemaining));
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,  int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.toString().isEmpty())
                {
                    currentAmountRemaining = String.valueOf(0);
                }
            }
        });; */


        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser)
            {
                if (newUserInputType.equals("QUANTITY"))
                {
                    quantityRemainingView.setText(String.valueOf(progress), TextView.BufferType.EDITABLE);
                }
                else
                {
                    amountRemainingView.setText(String.valueOf(progress), TextView.BufferType.EDITABLE);
                }
            }
        });

        /*int currentAmountRemain = Integer.valueOf(currentAmountRemaining);
        int newAmountRemaining = seekbar.getProgress();

        if (newAmountRemaining - currentAmountRemain != 0)
        {
            currentAmountRemaining = String.valueOf(newAmountRemaining);
            amountRemainingView.setText(currentAmountRemaining, TextView.BufferType.EDITABLE);
        } */


        //WHEN FINISHED IS PRESSED
        Button finishEditItem = (Button) findViewById(R.id.finish_edit_cupboard_item);
        finishEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent data = new Intent();

                String newName = nameView.getText().toString();
                String newDayBought = dayBoughtView.getText().toString();
                String newDayExpriy = dayExpiryView.getText().toString();
                String newDaysRemaining = daysRemainingView.getText().toString();
                String newUserInputTypeFinal = newUserInputType;
                String newQuantityBought = quantityBoughtView.getText().toString();
                String newQuantityRemaining = quantityRemainingView.getText().toString();
                String newAmountBought = amountBoughtView.getText().toString();
                String newAmountRemaining = amountRemainingView.getText().toString();

                FoodCupboardItem updatedFCItem = new FoodCupboardItem(newName, newDayBought,newDayExpriy,newDaysRemaining,newUserInputTypeFinal,newQuantityBought,newQuantityRemaining,
                        newAmountBought, newAmountRemaining);

                if (newUserInputType.equals("QUANTITY"))
                {
                    updatedFCItem.setAmountBought("0");
                    updatedFCItem.setAmountRemanining("0");
                }
                else if (newUserInputType.equals("AMOUNT"))
                {
                    updatedFCItem.setQuantityBought("1");
                    updatedFCItem.setQuantityRemaining("1");
                }
                data.putExtra("editedItem",updatedFCItem);
                setResult(RESULT_OK, data);
                finish();
            }
        }
        );
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                expiryDate = data.getStringExtra("newExpiryDate");
                dayExpiryView.setText(expiryDate);
            }
        }
    }
    private boolean containsCharacters(String s)
    {
        int lengthString = s.length();

        for (int i=0; i<lengthString;i++)
        {
            for (int j=0;j<10;j++)
            {
                String digit = s.substring(i, i+1);
                if (digit.equals(String.valueOf(j)))
                {

                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }
}
