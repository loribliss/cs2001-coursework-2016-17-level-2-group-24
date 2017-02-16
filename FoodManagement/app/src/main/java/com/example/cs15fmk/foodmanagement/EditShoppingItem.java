package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.data;
import static android.R.attr.priority;

//remeber to INCLUDE ERROR CHECK FOR BLANK ENTRIES
public class EditShoppingItem extends AppCompatActivity {

    private String newPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_item);

        ShoppingListItem a = getIntent().getParcelableExtra("editItem");
        String currentName = a.getItemName();
        String currentQuantity = a.getItemQuantity();
        String currentPriority = a.getItemPriority();
        String dateCreated = a.getItemDateCreated();

        newPriority = currentPriority;

        //final String currentName = getIntent().getStringExtra("Current name");
        final EditText name = (EditText) findViewById(R.id.shoppingListName_Edit);
        name.setText(currentName, TextView.BufferType.EDITABLE);

        //final String currentQuantity = getIntent().getStringExtra("Current amount");
        final EditText quantity = (EditText) findViewById(R.id.shoppingListQuantity_Edit);
        quantity.setText(currentQuantity, TextView.BufferType.EDITABLE);

        //String dateCreated = getIntent().getStringExtra("Date created");
        EditText dateCreatedView = (EditText) findViewById(R.id.shoppingListDateCreated_Edit);
        dateCreatedView.setText(dateCreated);

        //final String currentPriority = getIntent().getStringExtra("Current priority");
        //final TextView priority = (TextView) findViewById(R.id.edit_priority);
        //priority.setText(currentPriority, TextView.BufferType.EDITABLE);

        ImageView highPriorityCircle = (ImageView)findViewById(R.id.high_priority_circle);
        ImageView mediumPriorityCircle = (ImageView)findViewById(R.id.medium_priority_circle);
        ImageView lowPriorityCircle = (ImageView)findViewById(R.id.low_priority_circle);
        TextView highPriorityText = (TextView)findViewById(R.id.high_priority_text);
        TextView mediumPriorityText = (TextView)findViewById(R.id.medium_priority_text);
        TextView lowPriorityText = (TextView)findViewById(R.id.low_priority_text);

        if (currentPriority.equals("High"))
        {
            highPriorityCircle.setImageResource(R.drawable.circle_red_selected);
            highPriorityText.setTextColor(ContextCompat.getColor(this, R.color.colourBlueSelected));
        }
        else if (currentPriority.equals("Medium"))
        {
            mediumPriorityCircle.setImageResource(R.drawable.circle_yellow_selected);
            mediumPriorityText.setTextColor(ContextCompat.getColor(this, R.color.colourBlueSelected));
        }
        else
        {
            lowPriorityCircle.setImageResource(R.drawable.circle_green_selected);
            lowPriorityText.setTextColor(ContextCompat.getColor(this, R.color.colourBlueSelected));
        }

        highPriorityCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highPriorityCircle.setImageResource(R.drawable.circle_red_selected);
                highPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlueSelected));
                newPriority = "High";

                mediumPriorityCircle.setImageResource(R.drawable.circle_yellow);
                mediumPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlack));
                lowPriorityCircle.setImageResource(R.drawable.circle_green);
                lowPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlack));
            }
        });

        mediumPriorityCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediumPriorityCircle.setImageResource(R.drawable.circle_yellow_selected);
                mediumPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlueSelected));
                newPriority = "Medium";

                highPriorityCircle.setImageResource(R.drawable.circle_red);
                highPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlack));
                lowPriorityCircle.setImageResource(R.drawable.circle_green);
                lowPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlack));
            }
        });

        lowPriorityCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowPriorityCircle.setImageResource(R.drawable.circle_green_selected);
                lowPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlueSelected));
                newPriority = "Low";

                highPriorityCircle.setImageResource(R.drawable.circle_red);
                highPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlack));
                mediumPriorityCircle.setImageResource(R.drawable.circle_yellow);
                mediumPriorityText.setTextColor(ContextCompat.getColor(EditShoppingItem.this, R.color.colourBlack));
            }
        });

        Button editItem = (Button) findViewById(R.id.completeEditShoppingListItem);
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                String newName = name.getText().toString();
                String newAmount = quantity.getText().toString();
                String position = getIntent().getStringExtra("Position");

                if (checkEntry(name, newName, "name") == false | checkEntry(quantity, newAmount, "amount") == false)
                {
                    Toast.makeText(EditShoppingItem.this, "One or more entries are incomplete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ShoppingListItem item = new ShoppingListItem(newName, newAmount, newPriority, dateCreated, false); //BOOLEAN ISSUE
                    data.putExtra("completeEditItem", item);
                    //data.putExtra("newName", newName);
                    //data.putExtra("newAmount", newAmount);
                    //data.putExtra("newPriority", newPriority);
                    data.putExtra("position", position);

                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        }
        );
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

