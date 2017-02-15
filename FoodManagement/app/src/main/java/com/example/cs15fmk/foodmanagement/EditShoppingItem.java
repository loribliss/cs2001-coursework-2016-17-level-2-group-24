package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;

//remeber to INCLUDE ERROR CHECK FOR BLANK ENTRIES
public class EditShoppingItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_item);

        ShoppingListItem a = getIntent().getParcelableExtra("editItem");
        String currentname = a.getItemName();
        String currentamount = a.getItemAmount();
        String currentpriority = a.getItemPriority();


        //final String currentname = getIntent().getStringExtra("Current name");
        final EditText name = (EditText) findViewById(R.id.edit_name);
        name.setText(currentname, TextView.BufferType.EDITABLE);

        //final String currentamount = getIntent().getStringExtra("Current amount");
        final EditText amount = (EditText) findViewById(R.id.edit_amount);
        amount.setText(currentamount, TextView.BufferType.EDITABLE);

        //final String currentpriority = getIntent().getStringExtra("Current priority");
        final EditText priority = (EditText) findViewById(R.id.edit_priority);
        priority.setText(currentpriority, TextView.BufferType.EDITABLE);

        Button editItem = (Button) findViewById(R.id.complete);
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                String newName = name.getText().toString();
                String newAmount = amount.getText().toString();
                String newPriority = priority.getText().toString();
                String position = getIntent().getStringExtra("Position");

                if (checkEntry(name, newName, "name") == false | checkEntry(amount, newAmount, "amount") == false | checkEntry(priority, newPriority, "priority") == false)
                {
                    Toast.makeText(EditShoppingItem.this, "One or more entries are incomplete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ShoppingListItem item = new ShoppingListItem(newName, newAmount, newPriority, false); //BOOLEAN ISSUE
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

