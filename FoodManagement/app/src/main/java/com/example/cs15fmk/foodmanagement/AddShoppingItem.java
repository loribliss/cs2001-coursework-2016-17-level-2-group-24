package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
<<<<<<< HEAD
=======
import android.support.v4.content.ContextCompat;
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;

=======
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
import static android.view.View.Z;

public class AddShoppingItem extends AppCompatActivity {

<<<<<<< HEAD
=======
    private String priority = "";
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_item);

<<<<<<< HEAD
        Button addNewItem = (Button)findViewById(R.id.complete);
        addNewItem.setOnClickListener(new View.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View view)
                                          {
                                              Intent data = new Intent();
                                              EditText nameView = (EditText) findViewById(R.id.new_name);
                                              String name = nameView.getText().toString();
                                              boolean checkName = checkEntry(nameView, name, "name");

                                              EditText amountView = (EditText) findViewById(R.id.new_amount);
                                              String amount = amountView.getText().toString();
                                              boolean checkAmount = checkEntry(amountView, amount, "amount");

                                              EditText priorityView = (EditText) findViewById(R.id.new_priority);
                                              String priority = priorityView.getText().toString();
                                              boolean checkPriority = checkEntry(priorityView, priority, "priority");

                                              if (checkName == false | checkAmount == false | checkPriority == false)
                                              {
                                                  Toast.makeText(AddShoppingItem.this, "One or entries are incomplete", Toast.LENGTH_SHORT).show();
                                              }
                                              else
                                              {
                                                  ShoppingListItem item = new ShoppingListItem(name, amount, priority, false); //BOOLEAN ISSUE
                                                  data.putExtra("completeAddItem", item);

                                                  //data.putExtra("name",name);
                                                  //data.putExtra("amount",amount);
                                                  //data.putExtra("priority",priority);
                                                  setResult(RESULT_OK, data);
                                                  finish();
                                              }
                                          }
                                      }
=======
        ImageView highPriorityCircle = (ImageView)findViewById(R.id.high_priority_circle_add);
        ImageView mediumPriorityCircle = (ImageView)findViewById(R.id.medium_priority_circle_add);
        ImageView lowPriorityCircle = (ImageView)findViewById(R.id.low_priority_circle_add);
        TextView highPriorityText = (TextView)findViewById(R.id.high_priority_text_add);
        TextView mediumPriorityText = (TextView)findViewById(R.id.medium_priority_text_add);
        TextView lowPriorityText = (TextView)findViewById(R.id.low_priority_text_add);

        highPriorityCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highPriorityCircle.setImageResource(R.drawable.circle_red_selected);
                highPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlueSelected));
                priority = "High";

                mediumPriorityCircle.setImageResource(R.drawable.circle_yellow);
                mediumPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlack));
                lowPriorityCircle.setImageResource(R.drawable.circle_green);
                lowPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlack));
            }
        });

        mediumPriorityCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediumPriorityCircle.setImageResource(R.drawable.circle_yellow_selected);
                mediumPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlueSelected));
                priority = "Medium";

                highPriorityCircle.setImageResource(R.drawable.circle_red);
                highPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlack));
                lowPriorityCircle.setImageResource(R.drawable.circle_green);
                lowPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlack));
            }
        });

        lowPriorityCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowPriorityCircle.setImageResource(R.drawable.circle_green_selected);
                lowPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlueSelected));
                priority = "Low";

                highPriorityCircle.setImageResource(R.drawable.circle_red);
                highPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlack));
                mediumPriorityCircle.setImageResource(R.drawable.circle_yellow);
                mediumPriorityText.setTextColor(ContextCompat.getColor(AddShoppingItem.this, R.color.colourBlack));
            }
        });

        Button addNewItem = (Button)findViewById(R.id.completeAddShoppingListItem);
        addNewItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent data = new Intent();
                EditText nameView = (EditText) findViewById(R.id.newFCItem_name);
                String name = nameView.getText().toString();
                boolean checkName = checkEntry(nameView, name, "name");

                EditText amountView = (EditText) findViewById(R.id.newFCItem_quantity);
                String amount = amountView.getText().toString();
                boolean checkAmount = checkEntry(amountView, amount, "amount");

                TextView priorityView = (TextView)findViewById(R.id.add_priority);
                boolean checkPriority = checkPriority(priorityView, priority);

                if (checkName == false | checkAmount == false | checkPriority == false)
                {
                    Toast.makeText(AddShoppingItem.this, "One or entries are incomplete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String todaysDate = formatter.format(date);

                    ShoppingListItem item = new ShoppingListItem(name, amount, priority, todaysDate, false); //BOOLEAN ISSUE
                    data.putExtra("completeAddItem", item);
                    //data.putExtra("name",name);
                    //data.putExtra("amount",amount);
                    //data.putExtra("priority",priority);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        }
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
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
<<<<<<< HEAD
=======
    private boolean checkPriority(TextView view, String entry)
    {
        if (entry.isEmpty())
        {
            view.setText("Priority: *Please Complete*");
            view.setTextColor(Color.RED);
            return false;
        }
        return true;
    }
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
}