package com.example.cs15fmk.foodmanagement;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        TextView nameTextView = (TextView) gridItemView.findViewById(R.id.name_food);
        nameTextView.setText(currentItem.getName());

        TextView daysRemaniningTextView = (TextView) gridItemView.findViewById(R.id.days_remaining);
        daysRemaniningTextView.setText(currentItem.getDayBought());

        TextView expiryDateTextView = (TextView) gridItemView.findViewById(R.id.expiry_date);
        expiryDateTextView.setText(currentItem.getDayExpiry());

        String amountRemaining = String.valueOf(currentItem.getAmountRemaining());
        TextView amountRemainingTextView = (TextView) gridItemView.findViewById(R.id.amount_remaining);
        amountRemainingTextView.setText(amountRemaining);

        ProgressBar totalAmountBar = (ProgressBar) gridItemView.findViewById(R.id.progress_bar);
        totalAmountBar.setMax(currentItem.getAmountBought());
        totalAmountBar.setProgress(currentItem.getAmountRemaining());

        return gridItemView;
    }
}