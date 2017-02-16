package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.view.View.Z;

public class AddShoppingItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_item);

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