package com.example.cs15fmk.foodmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.R.attr.name;

public class ViewFoodCupboardItem extends AppCompatActivity {

    private String name;
    private String dayBought;
    private String dayExpiry;
    private String amountBought;
    private String amountRemaining;
    private ProgressBar progress;
    private TextView nameView;
    private TextView dayBoughtView;
    private TextView dayExpiryView;
    private TextView amountBoughtView;
    private TextView amountRemainingView;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_cupboard_item);

        name = getIntent().getStringExtra("Name");
        nameView = (TextView) findViewById(R.id.foodCupboardNameInput);

        dayBought = getIntent().getStringExtra("Day Bought");
        dayBoughtView = (TextView) findViewById(R.id.foodCupboardDayBoughtInput);

        dayExpiry = getIntent().getStringExtra("Day Expiry");
        dayExpiryView = (TextView) findViewById(R.id.foodCupboardDayExpiryInput);

        amountBought = getIntent().getStringExtra("Amount Bought");
        amountBoughtView = (TextView) findViewById(R.id.foodCupboardAmountBoughtInput);

        amountRemaining = getIntent().getStringExtra("Amount Remaining");
        amountRemainingView = (TextView) findViewById(R.id.foodCupboardAmountRemainingInput);

        progress = (ProgressBar) findViewById(R.id.progress_bar);
        updateViewPageText();

        position = getIntent().getStringExtra("Position");

        Button editCupboardItem = (Button)findViewById(R.id.edit_cupboard_item);
        editCupboardItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editCupboardItemEntry(name, dayBought, dayExpiry, Integer.valueOf(amountBought), Integer.valueOf(amountRemaining));
                //with edit, add position extra of empty string
            }
        }
        );

        Button deleteCupboardItem = (Button)findViewById(R.id.delete_cupboard_item);
        deleteCupboardItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent data = new Intent();
                data.putExtra("Position", position);
                data.putExtra("Edit", "no ");
                setResult(RESULT_OK, data);
                finish();
            }
        }
        );

        Button finishedViewing = (Button)findViewById(R.id.finish_viewing_cupboard_item);
        finishedViewing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent data = new Intent();
                data.putExtra("updatedName", name);
                data.putExtra("updatedDayBought", dayBought);
                data.putExtra("updatedDayExpiry",dayExpiry);
                data.putExtra("updatedAmountBought",amountBought);
                data.putExtra("updatedAmountRemaining", amountRemaining);
                data.putExtra("Position", position);
                data.putExtra("Edit", "yes");
                setResult(RESULT_OK, data);
                finish();
            }
        }
        );
    }

    public void editCupboardItemEntry(String name, String dayBought, String dayExpiry, int amountBought, int amountRemanining)
    {
        Intent intent = new Intent(ViewFoodCupboardItem.this, EditFoodCupboardItem.class);

        intent.putExtra("Current Name", name);
        intent.putExtra("Current Day Bought", dayBought);
        intent.putExtra("Current Day Expiry", dayExpiry);
        intent.putExtra("Current Amount Bought", String.valueOf(amountBought));
        intent.putExtra("Current Amount Remaining", String.valueOf(amountRemanining));
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                name = data.getStringExtra("New Name");
                dayBought = data.getStringExtra("New Day Bought");
                dayExpiry = data.getStringExtra("New Day Expiry");
                amountBought = data.getStringExtra("New Amount Bought");
                amountRemaining = data.getStringExtra("New Amount Remaining");
                updateViewPageText();
            }
        }
    }

    private void updateViewPageText()
    {
        nameView.setText(name);
        dayBoughtView.setText(dayBought);
        dayExpiryView.setText(dayExpiry);
        amountBoughtView.setText(amountBought);
        amountRemainingView.setText(amountRemaining);
        progress.setMax(Integer.valueOf(amountBought));
        progress.setProgress(Integer.valueOf(amountRemaining));
    }
}
