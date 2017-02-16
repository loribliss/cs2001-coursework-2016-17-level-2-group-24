package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodCupboard extends AppCompatActivity {

    private final ArrayList<FoodCupboardItem> cupboardItems = new ArrayList<>();
    private CupboardItemAdapter adapter;
    private GridView gridView;
    private Spinner spinner;
    private static final String[]paths = {"Filter", "item 1", "item 2", "item 3"}; //first item is displayed!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_food_cupboard);

        cupboardItems.add(new FoodCupboardItem("Chicken", "31/01/17", "07/02/17", 400, 250));
        cupboardItems.add(new FoodCupboardItem("Bread", "21/01/17", "08/02/17", 600, 300));
        cupboardItems.add(new FoodCupboardItem("Cereal", "11/01/17", "09/02/17", 750, 50));
        cupboardItems.add(new FoodCupboardItem("Beef", "01/01/17", "10/02/17", 1000, 450));
        cupboardItems.add(new FoodCupboardItem("Milk", "01/01/17", "10/02/17", 20000, 500));
        cupboardItems.add(new FoodCupboardItem("Wholemeal Rolls", "01/01/17", "10/02/17", 4, 2));
        cupboardItems.add(new FoodCupboardItem("Turkey rashers", "01/01/17", "10/02/17", 8, 2));
        cupboardItems.add(new FoodCupboardItem("Eggs", "01/01/17", "10/02/17", 12, 6));
        cupboardItems.add(new FoodCupboardItem("Nature Valley Bars", "01/01/17", "10/02/17", 5, 3));
        cupboardItems.add(new FoodCupboardItem("Hula Hoops", "01/01/17", "10/02/17", 10, 10));

        adapter = new CupboardItemAdapter (this, cupboardItems);
        gridView = (GridView) findViewById(R.id.grid_cupboard);
        gridView.setAdapter(adapter);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FoodCupboard.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                viewCupboardItemEntry(position);
            }
        });

        Button addNewItem = (Button) findViewById(R.id.add_new_cupboard_item);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(FoodCupboard.this, AddNewFCItemMenu.class);
                startActivityForResult(intent, 2);
            }
        } );
    }

    public void viewCupboardItemEntry(int position)
    {
        FoodCupboardItem item = cupboardItems.get(position);
        Intent intent = new Intent(FoodCupboard.this, ViewFoodCupboardItem.class);

        intent.putExtra("Name", item.getName());
        intent.putExtra("Day Bought", item.getDayBought());
        intent.putExtra("Day Expiry", item.getDayExpiry());

        intent.putExtra("Amount Bought", String.valueOf(item.getAmountBought()));
        intent.putExtra("Amount Remaining", String.valueOf(item.getAmountRemaining()));
        intent.putExtra("Position", String.valueOf(position));
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                int pos = Integer.valueOf(data.getStringExtra("Position"));

                if (data.getStringExtra("Edit").equals("yes"))
                {
                    //edit functionality, also need to beware finish functionalitym could set position as specific text e.g. none, edit to indicate intention
                    cupboardItems.get(pos).setName(data.getStringExtra("updatedName"));
                    cupboardItems.get(pos).setDayBought(data.getStringExtra("updatedDayBought"));
                    cupboardItems.get(pos).setDayExpiry(data.getStringExtra("updatedDayExpiry"));
                    cupboardItems.get(pos).setAmountBought(Integer.valueOf(data.getStringExtra("updatedAmountBought")));
                    cupboardItems.get(pos).setAmountRemanining(Integer.valueOf(data.getStringExtra("updatedAmountRemaining")));

                    Toast.makeText(this, "You have just updated " + cupboardItems.get(pos).getName(), Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();
                }

                else
                {
                    Toast.makeText(this, "You have just deleted " + cupboardItems.get(pos).getName(), Toast.LENGTH_SHORT).show();
                    cupboardItems.remove(pos);
                    adapter.notifyDataSetChanged(); //deletion does not change anything permanently if you exit the screen
                }
            }
        }

        if (requestCode == 2)
        {
            //add new item
            if (resultCode == RESULT_OK)
            {
                String name = data.getStringExtra("newFCName");
                String dayBought = data.getStringExtra("newFCDayBought");
                String dayExpiry = data.getStringExtra("newFCDayExpiry");
                String amountBought = data.getStringExtra("newFCAmountBought");
                int amount = Integer.valueOf(amountBought);

                cupboardItems.add(new FoodCupboardItem(name, dayBought, dayExpiry, amount, amount));
                adapter.notifyDataSetChanged();
            }
        }
    }

}