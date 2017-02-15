package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class EditFoodCupboardItem extends AppCompatActivity {

    private String currentAmountRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_cupboard_item);

        String currentName = getIntent().getStringExtra("Current Name");
        final EditText nameView = (EditText) findViewById(R.id.foodCupboardNameEdit);
        nameView.setText(currentName, TextView.BufferType.EDITABLE);

        String currentDayBought = getIntent().getStringExtra("Current Day Bought");
        final EditText dayBoughtView = (EditText) findViewById(R.id.foodCupboardDayBoughtEdit);
        dayBoughtView.setText(currentDayBought, TextView.BufferType.EDITABLE);

        String currentDayExpiry = getIntent().getStringExtra("Current Day Expiry");
        final EditText dayExpiryView = (EditText) findViewById(R.id.foodCupboardDayExpiryEdit);
        dayExpiryView.setText(currentDayExpiry, TextView.BufferType.EDITABLE);

        String currentAmountBought = getIntent().getStringExtra("Current Amount Bought");
        final EditText amountBoughtView = (EditText) findViewById(R.id.foodCupboardAmountBoughtEdit);
        amountBoughtView.setText(currentAmountBought, TextView.BufferType.EDITABLE);

        currentAmountRemaining = getIntent().getStringExtra("Current Amount Remaining");
        final EditText amountRemainingView = (EditText) findViewById(R.id.foodCupboardAmountRemainingEdit);
        amountRemainingView.setText(currentAmountRemaining, TextView.BufferType.EDITABLE);

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seek_bar);
        seekbar.setMax(Integer.valueOf(currentAmountBought));
        seekbar.setProgress(Integer.valueOf(currentAmountRemaining));

        amountRemainingView.addTextChangedListener(new TextWatcher(){
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
        });;


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
                amountRemainingView.setText(String.valueOf(progress), TextView.BufferType.EDITABLE);
            }
        });


        int currentAmountRemain = Integer.valueOf(currentAmountRemaining);
        int newAmountRemaining = seekbar.getProgress();

        if (newAmountRemaining - currentAmountRemain != 0)
        {
            currentAmountRemaining = String.valueOf(newAmountRemaining);
            amountRemainingView.setText(currentAmountRemaining, TextView.BufferType.EDITABLE);
        }

        Button finishEditItem = (Button) findViewById(R.id.finish_edit_cupboard_item);
        finishEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent data = new Intent();
                String newName = nameView.getText().toString();
                data.putExtra("New Name", newName);
                String newDayBought = dayBoughtView.getText().toString();
                data.putExtra("New Day Bought", newDayBought);
                String newDayExpriy = dayExpiryView.getText().toString();
                data.putExtra("New Day Expiry", newDayExpriy);
                String newAmountBought = amountBoughtView.getText().toString();
                data.putExtra("New Amount Bought", newAmountBought);
                String newAmountRemaining = amountRemainingView.getText().toString();
                data.putExtra("New Amount Remaining", newAmountRemaining);

                setResult(RESULT_OK, data);
                finish();
            }
        }
        );
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
