package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import java.util.Date;

import static android.R.attr.data;
import static android.R.attr.priority;

public class AddFoodCupboardItemManual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_cupboard_item_manual);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final String todaysDate = formatter.format(date);

        EditText newDayBoughtView = (EditText) findViewById(R.id.new_FC_dayBought);
        newDayBoughtView.setText(todaysDate);

        //error checking - e.g. day expiry must be valid calendar date!!!!!!
        //add text view to side of edit views which say what feature is being added

        Button addNewFCItem = (Button)findViewById(R.id.complete_FC);
        addNewFCItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                EditText newNameView = (EditText) findViewById(R.id.new_FC_name);
                String name = newNameView.getText().toString();
                boolean checkName = checkEntry(newNameView, name, "name");

                EditText newDayExpiryView = (EditText) findViewById(R.id.new_FC_dayExpiry);
                String dayExpiry = newDayExpiryView.getText().toString();
                boolean checkDayExpiry = checkEntry(newDayExpiryView, dayExpiry, "Day Expiry");

                EditText newAmountBought = (EditText) findViewById(R.id.new_FC_amountBought);
                String amountBoughtString = newAmountBought.getText().toString();
                boolean checkAmountBought = checkEntry(newAmountBought, amountBoughtString, "Amount Bought");

                //DatePicker date = (DatePicker) findViewById(R.id.date);
                //newDayExpiryView.setText(String.valueOf(date.getDayOfMonth()));

                if (checkName == false | checkDayExpiry == false | checkAmountBought == false)
                {
                    Toast.makeText(AddFoodCupboardItemManual.this, "One or entries are incomplete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent data = new Intent();
                    data.putExtra("new_FC_Name",name);
                    data.putExtra("new_FC_DayBought", todaysDate);
                    data.putExtra("new_FC_DayExpiry",dayExpiry);
                    data.putExtra("new_FC_AmountBought", amountBoughtString);

                    setResult(RESULT_OK, data);
                    finish();
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
