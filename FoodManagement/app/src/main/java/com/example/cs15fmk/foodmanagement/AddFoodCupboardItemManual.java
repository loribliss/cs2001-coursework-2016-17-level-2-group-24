package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static android.R.attr.button;
import static android.R.attr.data;
import static android.R.attr.priority;
import static android.view.View.Z;

public class AddFoodCupboardItemManual extends AppCompatActivity {

    private String currentUserInputType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_cupboard_item_manual);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final String todaysDate = formatter.format(date);

        EditText newDayBoughtView = (EditText) findViewById(R.id.newFCDayBought);
        newDayBoughtView.setText(todaysDate);

        EditText daysRemainingView = (EditText)findViewById(R.id.newFCDaysRemaining);
        daysRemainingView.setText("5");

        TextView hintView = (TextView)findViewById(R.id.newFCHintBox);
        EditText quantityView = (EditText)findViewById(R.id.newFCQuantityBought);
        EditText amountView = (EditText)findViewById(R.id.newFCAmountBought);
        quantityView.setVisibility(View.GONE);
        amountView.setVisibility(View.GONE);

        Button quantityOption = (Button)findViewById(R.id.newFCQuantityOption);
        Button amountOption = (Button)findViewById(R.id.newFCAmountOption);
        Button bothOption = (Button)findViewById(R.id.newFCBothQuantityAndAmountOption);
        //error checking - e.g. day expiry must be valid calendar date!!!!!!
        //restoring defaults


        quantityOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                quantityView.setText("");
                quantityView.setVisibility(View.VISIBLE);
                amountView.setText("");
                amountView.setVisibility(View.VISIBLE);
                amountOption.setBackgroundColor(Color.GRAY);
                bothOption.setBackgroundColor(Color.GRAY);

                currentUserInputType = "QUANTITY";
                hintView.setText("Hint: Use this option for 5 apples");
                quantityOption.setBackgroundColor(ContextCompat.getColor(AddFoodCupboardItemManual.this, R.color.colourBlueSelected));

                amountView.setVisibility(View.GONE); //try invisible as well
            }
        });

        amountOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                quantityView.setText("");
                quantityView.setVisibility(View.VISIBLE);
                amountView.setText("");
                amountView.setVisibility(View.VISIBLE);
                quantityOption.setBackgroundColor(Color.GRAY);
                bothOption.setBackgroundColor(Color.GRAY);

                currentUserInputType = "AMOUNT";
                hintView.setText("Hint: Use this option for 1 BOTTLE OF 1000ml of Milk");
                amountOption.setBackgroundColor(ContextCompat.getColor(AddFoodCupboardItemManual.this, R.color.colourBlueSelected));
                //quantityOption.setText("1");
                quantityView.setVisibility(View.GONE); //try invisible as well
            }
        });

        bothOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                quantityView.setText("");
                quantityView.setVisibility(View.VISIBLE);
                amountView.setText("");
                amountView.setVisibility(View.VISIBLE);
                quantityOption.setBackgroundColor(Color.GRAY);
                amountOption.setBackgroundColor(Color.GRAY);

                currentUserInputType = "QUANTITY&AMOUNT";
                hintView.setText("Hint: Use this option for >1 BOTTLE OF 1000ml of Milk");
                bothOption.setBackgroundColor(ContextCompat.getColor(AddFoodCupboardItemManual.this, R.color.colourBlueSelected));
            }
        });


        Button addNewFCItem = (Button)findViewById(R.id.completeNewFCManual);
        addNewFCItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText newNameView = (EditText) findViewById(R.id.newFCName);
                String name = newNameView.getText().toString();

                EditText newDayExpiryView = (EditText) findViewById(R.id.newFCDayExpiry);
                String dayExpiry = newDayExpiryView.getText().toString();

                String quantityBoughtString = quantityView.getText().toString();

                String amountBoughtString = amountView.getText().toString();


                if (currentUserInputType.equals("QUANTITY")) //"5" FOR DAYS REMAINING NEEDS CHANGING
                {
                    boolean checkName = checkEntry(newNameView, name, "name");
                    boolean checkDayExpiry = checkEntry(newDayExpiryView, dayExpiry, "Day Expiry");
                    boolean checkQuantityBought = checkEntry(quantityView, quantityBoughtString, "Quantity Bought");

                    if (checkName == false | checkDayExpiry == false | checkQuantityBought == false)
                    {
                        Toast.makeText(AddFoodCupboardItemManual.this, "One or entries are incomplete", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent data = new Intent();
                        FoodCupboardItem newItem = new FoodCupboardItem(name,todaysDate,dayExpiry,"5",currentUserInputType,quantityBoughtString,quantityBoughtString,
                                "0","0");
                        data.putExtra("newItem",newItem);
                        //data.putExtra("new_FC_Name",name);
                        //data.putExtra("new_FC_DayBought", todaysDate);
                        //data.putExtra("new_FC_DayExpiry",dayExpiry);
                        //data.putExtra("new_FC_AmountBought", amountBoughtString);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                }
                else if (currentUserInputType.equals("AMOUNT"))
                {
                    boolean checkName = checkEntry(newNameView, name, "name");
                    boolean checkDayExpiry = checkEntry(newDayExpiryView, dayExpiry, "Day Expiry");
                    boolean checkAmountBought = checkEntry(amountView, amountBoughtString, "Amount Bought");

                    if (checkName == false | checkDayExpiry == false | checkAmountBought == false)
                    {
                        Toast.makeText(AddFoodCupboardItemManual.this, "One or entries are incomplete", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent data = new Intent();
                        FoodCupboardItem newItem = new FoodCupboardItem(name,todaysDate,dayExpiry,"5",currentUserInputType,"1","1",
                                amountBoughtString,amountBoughtString);
                        data.putExtra("newItem",newItem);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                }
                else
                {
                    boolean checkName = checkEntry(newNameView, name, "name");
                    boolean checkDayExpiry = checkEntry(newDayExpiryView, dayExpiry, "Day Expiry");
                    boolean checkQuantityBought = checkEntry(quantityView, quantityBoughtString, "Quantity Bought");
                    boolean checkAmountBought = checkEntry(amountView, amountBoughtString, "Amount Bought");

                    if (checkName == false | checkDayExpiry == false | checkQuantityBought == false | checkAmountBought == false)
                    {
                        Toast.makeText(AddFoodCupboardItemManual.this, "One or entries are incomplete", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent data = new Intent();
                        FoodCupboardItem newItem = new FoodCupboardItem(name,todaysDate,dayExpiry,"5",currentUserInputType,quantityBoughtString,quantityBoughtString,
                                amountBoughtString,amountBoughtString);
                        data.putExtra("newItem",newItem);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                }
                //add radio option pane maybe - could influence a draggable progress bar to set how much bought
            }
        });
    }
    private boolean checkEntry(EditText view, String entry, String title)
    {
        if (entry.isEmpty())
        {
            view.setHint(title + "   " + "* Please Complete *");
            view.setHintTextColor(Color.RED);
            return false;
        }
        return true;
    }
}
