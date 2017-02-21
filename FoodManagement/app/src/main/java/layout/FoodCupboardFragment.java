package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs15fmk.foodmanagement.AddNewFCItemMenu;
import com.example.cs15fmk.foodmanagement.CupboardItemAdapter;
import com.example.cs15fmk.foodmanagement.FoodCupboardItem;
import com.example.cs15fmk.foodmanagement.InitialiseFoodCupboardArray;
import com.example.cs15fmk.foodmanagement.InitialiseShoppingListArray;
import com.example.cs15fmk.foodmanagement.R;
import com.example.cs15fmk.foodmanagement.ViewFoodCupboardItem;

import java.util.ArrayList;

import static android.R.attr.name;
import static android.app.Activity.RESULT_OK;

public class FoodCupboardFragment extends Fragment {

    private ArrayList<FoodCupboardItem> foodCupboardItems = new ArrayList<>();
    private CupboardItemAdapter adapter;
    private GridView gridView;
    private Spinner spinner;
    private Activity activity;

    private static final String[]paths = {"All", "Alphabetical", "Days Left", "item 3"}; //first item is displayed!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_food_cupboard, container, false);
        activity = getActivity();

        foodCupboardItems = InitialiseFoodCupboardArray.getArray();

        adapter = new CupboardItemAdapter (activity, foodCupboardItems);
        gridView = (GridView) view.findViewById(R.id.grid_cupboard);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                viewCupboardItemEntry(position);
            }
        });


        spinner = (Spinner)view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 1)
                {
                    Toast.makeText(activity, "You have just selected " + paths[position], Toast.LENGTH_SHORT).show();
                }
                else if (position ==2)
                {
                    Toast.makeText(activity, "You have just selected " + paths[position], Toast.LENGTH_SHORT).show();
                }
                else if (position ==3)
                {
                    Toast.makeText(activity, "You have just selected " + paths[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button addNewItem = (Button) view.findViewById(R.id.add_new_cupboard_item);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(activity, AddNewFCItemMenu.class);
                startActivityForResult(intent, 2);
            }
        } );
        return view;
    }

    public void viewCupboardItemEntry(int position)
    {
        FoodCupboardItem item = foodCupboardItems.get(position);
        Intent intent = new Intent(activity, ViewFoodCupboardItem.class);
        intent.putExtra("POSITION", String.valueOf(position));
        intent.putExtra("viewFoodCupboardItem", item);

        //intent.putExtra("Name", item.getName());
        //intent.putExtra("Day Bought", item.getDayBought());
        //intent.putExtra("Day Expiry", item.getDayExpiry());
        //intent.putExtra("Amount Bought", String.valueOf(item.getAmountBought()));
        //intent.putExtra("Amount Remaining", String.valueOf(item.getAmountRemaining()));

        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                int pos = Integer.valueOf(data.getStringExtra("Position"));

                if (data.getStringExtra("Edit").equals("yes"))
                {
                    FoodCupboardItem editedItem = data.getParcelableExtra("updatedItem");
                    foodCupboardItems.set(pos, editedItem);
                    adapter.notifyDataSetChanged();
                    editMasterList();
                    Toast.makeText(activity, "You have just updated " + foodCupboardItems.get(pos).getName(), Toast.LENGTH_SHORT).show();

                    //COMPLETE NEXT!
                    //edit functionality, also need to beware finish functionality could set position as specific text e.g. none, edit to indicate intention
                    /* foodCupboardItems.get(pos).setName(data.getStringExtra("updatedName"));
                    foodCupboardItems.get(pos).setDayBought(data.getStringExtra("updatedDayBought"));
                    foodCupboardItems.get(pos).setDayExpiry(data.getStringExtra("updatedDayExpiry"));
                    foodCupboardItems.get(pos).setAmountBought(Integer.valueOf(data.getStringExtra("updatedAmountBought")));
                    foodCupboardItems.get(pos).setAmountRemanining(Integer.valueOf(data.getStringExtra("updatedAmountRemaining")));
                     */
                }

                else
                {
                    Toast.makeText(activity, "You have just deleted " + foodCupboardItems.get(pos).getName(), Toast.LENGTH_SHORT).show();
                    foodCupboardItems.remove(pos);
                    adapter.notifyDataSetChanged(); //deletion does not change anything permanently if you exit the screen
                    editMasterList();
                }
            }
        }

        if (requestCode == 2)
        {
            //add new item
            if (resultCode == RESULT_OK)
            {
                FoodCupboardItem newItem = data.getParcelableExtra("returnNewItem");
                foodCupboardItems.add(newItem);
                adapter.notifyDataSetChanged();
                editMasterList();
            }
        }
    }
    private void editMasterList()
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                InitialiseFoodCupboardArray.updateArray(foodCupboardItems);
            }
        });
    }

}