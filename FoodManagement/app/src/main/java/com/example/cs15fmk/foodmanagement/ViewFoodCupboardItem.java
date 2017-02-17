package com.example.cs15fmk.foodmanagement;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.name;

public class ViewFoodCupboardItem extends AppCompatActivity {

    private FoodCupboardItem currentItem;
    private FoodCupboardItem updatedItem;
    /*private String name;
    private String dayBought;
    private String dayExpiry;
    private String daysRemaining;
    private String userInputType;
    private String quantityBought;
    private String quantityRemaining;
    private String amountBought;
    private String amountRemaining; */

    private ProgressBar progress;
    private TextView nameView;
    private TextView dayBoughtView;
    private TextView dayExpiryView;
    private TextView daysRemainingView;
    private TextView quantityBoughtView;
    private TextView quantityRemainingView;
    private TextView amountBoughtView;
    private TextView amountRemainingView;

    private String position; //one value alwayss

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_cupboard_item);

        currentItem = getIntent().getParcelableExtra("viewFoodCupboardItem");

        nameView = (TextView) findViewById(R.id.foodCupboardNameInput);
        dayBoughtView = (TextView) findViewById(R.id.foodCupboardDayBoughtInput);
        dayExpiryView = (TextView) findViewById(R.id.foodCupboardDayExpiryInput);
        daysRemainingView = (TextView) findViewById(R.id.foodCupboardDaysRemainingInput);
        quantityBoughtView = (TextView) findViewById(R.id.foodCupboardQuantityBoughtInput);
        quantityRemainingView = (TextView) findViewById(R.id.foodCupboardQuantityRemainingInput);
        amountBoughtView = (TextView) findViewById(R.id.foodCupboardAmountBoughtInput);
        amountRemainingView = (TextView) findViewById(R.id.foodCupboardAmountRemainingInput);
        progress = (ProgressBar) findViewById(R.id.progressBarViewFoodCupboardItem);
        updateViewPageText();

        position = getIntent().getStringExtra("POSITION");

        Button editCupboardItem = (Button)findViewById(R.id.edit_cupboard_item);
        editCupboardItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ViewFoodCupboardItem.this, EditFoodCupboardItem.class);
                intent.putExtra("editItem", currentItem);
                startActivityForResult(intent, 1);

                //editCupboardItemEntry(name, dayBought, dayExpiry, Integer.valueOf(amountBought), Integer.valueOf(amountRemaining));
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
                data.putExtra("Edit", "no");
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
                data.putExtra("updatedItem", currentItem);
                data.putExtra("Position", position);
                data.putExtra("Edit", "yes");
                setResult(RESULT_OK, data);
                finish();
                /*data.putExtra("updatedName", name);
                data.putExtra("updatedDayBought", dayBought);
                data.putExtra("updatedDayExpiry",dayExpiry);
                data.putExtra("updatedAmountBought",amountBought);
                data.putExtra("updatedAmountRemaining", amountRemaining); */
            }
        }
        );
    }

    /*public void editCupboardItemEntry(String name, String dayBought, String dayExpiry, int amountBought, int amountRemanining)
    {
        Intent intent = new Intent(ViewFoodCupboardItem.this, EditFoodCupboardItem.class);

        intent.putExtra("Current Name", name);
        intent.putExtra("Current Day Bought", dayBought);
        intent.putExtra("Current Day Expiry", dayExpiry);
        intent.putExtra("Current Amount Bought", String.valueOf(amountBought));
        intent.putExtra("Current Amount Remaining", String.valueOf(amountRemanining));
        startActivityForResult(intent, 1);
    } */

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                updatedItem = data.getParcelableExtra("editedItem");
                currentItem = updatedItem;
                updateViewPageText();
            }
        }
    }

    private void updateViewPageText()
    {
        nameView.setText(currentItem.getName());
        dayBoughtView.setText(currentItem.getDayBought());
        dayExpiryView.setText(currentItem.getDayExpiry());
        daysRemainingView.setText(currentItem.getDaysRemaining());

        if (currentItem.getUserInputType().equals("AMOUNT"))
        {
            quantityBoughtView.setText(currentItem.getQuantityBought());
            quantityRemainingView.setText(currentItem.getQuantityRemaining());
            amountBoughtView.setText(currentItem.getAmountBought());
            amountRemainingView.setText(currentItem.getAmountRemaining());
            progress.setMax(Integer.valueOf(currentItem.getAmountBought()));
            progress.setProgress(Integer.valueOf(currentItem.getAmountRemaining()));
        }
        else if (currentItem.getUserInputType().equals("QUANTITY&AMOUNT"))
        {
            quantityBoughtView.setText(currentItem.getQuantityBought());
            quantityRemainingView.setText(currentItem.getQuantityRemaining());
            amountBoughtView.setText(currentItem.getAmountBought());
            amountRemainingView.setText(currentItem.getAmountRemaining());
            progress.setMax(Integer.valueOf(currentItem.getAmountBought()));
            progress.setProgress(Integer.valueOf(currentItem.getAmountRemaining()));
        }
        else
        {
            quantityBoughtView.setText(currentItem.getQuantityBought());
            quantityRemainingView.setText(currentItem.getQuantityRemaining());
            TextView amountBoughtTitle = (TextView)findViewById(R.id.foodCupboardAmountBought);
            amountBoughtTitle.setTextColor(Color.GRAY);
            amountBoughtView.setText("");
            TextView amountRemainingTitle = (TextView)findViewById(R.id.foodCupboardAmountRemaining);
            amountRemainingTitle.setTextColor(Color.GRAY);
            amountRemainingView.setText("");

            progress.setMax(Integer.valueOf(currentItem.getQuantityBought()));
            progress.setProgress(Integer.valueOf(currentItem.getQuantityRemaining()));
        }

        int daysRemaining = Integer.valueOf(currentItem.getDaysRemaining());
        ImageView expiryStatusCicle = (ImageView)findViewById(R.id.daysRemainingCircleView);
        if (daysRemaining<=2)
        {
            expiryStatusCicle.setImageResource(R.drawable.circle_red);
            progress.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.MULTIPLY); //changed colour but not to green !
        }
        else if (daysRemaining<=6)
        {
            expiryStatusCicle.setImageResource(R.drawable.circle_yellow);
        }
        else
        {
            expiryStatusCicle.setImageResource(R.drawable.circle_green);
        }
    }
}
