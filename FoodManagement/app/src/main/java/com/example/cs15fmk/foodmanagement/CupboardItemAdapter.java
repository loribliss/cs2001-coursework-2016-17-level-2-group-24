package com.example.cs15fmk.foodmanagement;


import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;



public class CupboardItemAdapter extends ArrayAdapter<FoodCupboardItem> {
    public CupboardItemAdapter(Activity context, ArrayList<FoodCupboardItem> cupboarditems) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, cupboarditems);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if(gridItemView == null)
        {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.food_cupboard_template, parent, false);
        }

        // Get the object located at this position in the list
        FoodCupboardItem currentItem = getItem(position);
        int daysRemaining = Integer.valueOf(currentItem.getDaysRemaining());

        TextView nameTextView = (TextView) gridItemView.findViewById(R.id.nameFoodCupboardItem);
        nameTextView.setText(currentItem.getName());

        TextView daysRemaniningTextView = (TextView) gridItemView.findViewById(R.id.daysRemainingFoodCupboardItem);
        daysRemaniningTextView.setText("DR: " + daysRemaining);

        ImageView expiryStatusCicle = (ImageView)gridItemView.findViewById(R.id.expiryStatusFoodCupboardItem);
        if (daysRemaining<=2)
        {
            expiryStatusCicle.setImageResource(R.drawable.circle_red);
        }
        else if (daysRemaining<=6)
        {
            expiryStatusCicle.setImageResource(R.drawable.circle_yellow);
        }
        else
        {
            expiryStatusCicle.setImageResource(R.drawable.circle_green);
        }

        TextView quantityRemainingTextView = (TextView) gridItemView.findViewById(R.id.quantityRemainingFoodCupboardItem);
        TextView amountRemainingTextView = (TextView) gridItemView.findViewById(R.id.amountRemainingFoodCupboardItem);
        ProgressBar totalAmountBar = (ProgressBar) gridItemView.findViewById(R.id.progressBarFoodCupboardItem);

        if (currentItem.getUserInputType().equals("AMOUNT"))
        {
            quantityRemainingTextView.setText("QR :" + currentItem.getQuantityRemaining());
            amountRemainingTextView.setText("AR: " + currentItem.getAmountRemaining());
            totalAmountBar.setMax(Integer.valueOf(currentItem.getAmountBought()));
            totalAmountBar.setProgress(Integer.valueOf(currentItem.getAmountRemaining()));
            //totalAmountBar.setBackgroundColor(Color.RED);
        }
        else if (currentItem.getUserInputType().equals("QUANTITY&AMOUNT"))
        {
            quantityRemainingTextView.setText("QR :" + currentItem.getQuantityRemaining());
            amountRemainingTextView.setText("AR: " + currentItem.getAmountRemaining());
            totalAmountBar.setMax(Integer.valueOf(currentItem.getAmountBought()));
            totalAmountBar.setProgress(Integer.valueOf(currentItem.getAmountRemaining()));
            //totalAmountBar.setBackgroundColor(Color.GREEN);
        }
        else
        {
            quantityRemainingTextView.setText("QR :" + currentItem.getQuantityRemaining());
            amountRemainingTextView.setText("AR :");
            totalAmountBar.setMax(Integer.valueOf(currentItem.getQuantityBought()));
            totalAmountBar.setProgress(Integer.valueOf(currentItem.getQuantityRemaining()));
            //totalAmountBar.setBackgroundColor(Color.RED);
        }
        return gridItemView;
    }
}