package com.example.cs15fmk.foodmanagement;

import android.app.Activity;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SItemAdapter extends ArrayAdapter<ShoppingListItem> {
    public SItemAdapter(Activity context, ArrayList<ShoppingListItem> shoppingitems) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, shoppingitems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.shopping_list_template, parent, false);
        }

        // Get the object located at this position in the list
        ShoppingListItem currentItem = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.shoppingListItemName);
        nameTextView.setText(currentItem.getItemName());

        TextView amountTextView = (TextView) listItemView.findViewById(R.id.shoppingListItemQuantity);
        amountTextView.setText(currentItem.getItemQuantity());

        //TextView priorityTextView = (TextView) listItemView.findViewById(R.id.shoppingListItemPriority);
        //priorityTextView.setText(currentItem.getItemPriority());

        ImageView priorityImageView = (ImageView) listItemView.findViewById(R.id.priority_circle);

        if (currentItem.getItemPriority().equals("High"))
        {
            priorityImageView.setImageResource(R.drawable.circle_red);
        }
        else if (currentItem.getItemPriority().equals("Medium"))
        {
            priorityImageView.setImageResource(R.drawable.circle_yellow);
        }
        else
        {
            priorityImageView.setImageResource(R.drawable.circle_green);
        }

        CheckBox checkBox = (CheckBox) listItemView.findViewById(R.id.check_box);

        if (currentItem.getItemCheckBoxState() == true)
        {
            checkBox.setChecked(true);
        }

        return listItemView;
    }
}
