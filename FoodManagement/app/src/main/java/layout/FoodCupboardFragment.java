package layout;

import android.app.Activity;
import android.content.Intent;
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
import com.example.cs15fmk.foodmanagement.R;
import com.example.cs15fmk.foodmanagement.ViewFoodCupboardItem;

import java.util.ArrayList;

import static android.R.attr.name;
import static android.app.Activity.RESULT_OK;

public class FoodCupboardFragment extends Fragment {

    private final ArrayList<FoodCupboardItem> cupboardItems = new ArrayList<>();
    private CupboardItemAdapter adapter;
    private GridView gridView;
    private Spinner spinner;
    private Activity activity;

    private static final String[]paths = {"Filter", "item 1", "item 2", "item 3"}; //first item is displayed!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_food_cupboard, container, false);
        activity = getActivity();

        cupboardItems.add(new FoodCupboardItem("Chicken", "31/01/2017", "07/02/2017", "8", "AMOUNT", "1", "1", "400", "300"));
        cupboardItems.add(new FoodCupboardItem("Bread", "21/01/2017", "08/02/2017", "6", "QUANTITY", "12", "6","0", "0"));
        cupboardItems.add(new FoodCupboardItem("Cereal", "11/01/2017", "09/02/2017", "30", "AMOUNT", "1","1", "750", "50"));
        cupboardItems.add(new FoodCupboardItem("Beef", "01/01/2017", "27/02/2017", "5", "QUANTITY&AMOUNT", "3","1","1000","450"));
        cupboardItems.add(new FoodCupboardItem("Milk", "05/01/2017", "10/03/2017", "5", "AMOUNT", "1","1","2000", "500"));
        cupboardItems.add(new FoodCupboardItem("Wholemeal Rolls", "15/12/2016", "15/03/2017", "2","QUANTITY","4","2","0","0"));
        cupboardItems.add(new FoodCupboardItem("Turkey rashers", "11/01/2017", "26/02/2017", "14", "AMOUNT", "1", "1","8","2"));
        cupboardItems.add(new FoodCupboardItem("Eggs", "18/02/2017", "01/03/2017", "16", "QUANTITY&AMOUNT", "2", "2", "12", "6"));
        cupboardItems.add(new FoodCupboardItem("Beef", "01/01/2017", "28/02/2017", "5", "QUANTITY&AMOUNT", "3","1","1000","450"));
        cupboardItems.add(new FoodCupboardItem("Milk", "01/01/2017", "24/02/2017", "5", "AMOUNT", "1","1","2000", "500"));
        cupboardItems.add(new FoodCupboardItem("Wholemeal Rolls", "23/01/2017", "03/03/2017", "2","QUANTITY","4","2","0","0"));
        cupboardItems.add(new FoodCupboardItem("Turkey rashers", "07/02/2017", "10/03/2017", "14", "AMOUNT", "1", "1","8","2"));

        adapter = new CupboardItemAdapter (activity, cupboardItems);
        gridView = (GridView) view.findViewById(R.id.grid_cupboard);
        gridView.setAdapter(adapter);

        spinner = (Spinner)view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                viewCupboardItemEntry(position);
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
        FoodCupboardItem item = cupboardItems.get(position);
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
                    cupboardItems.set(pos, editedItem);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(activity, "You have just updated " + cupboardItems.get(pos).getName(), Toast.LENGTH_SHORT).show();

                    //COMPLETE NEXT!
                    //edit functionality, also need to beware finish functionality could set position as specific text e.g. none, edit to indicate intention
                    /* cupboardItems.get(pos).setName(data.getStringExtra("updatedName"));
                    cupboardItems.get(pos).setDayBought(data.getStringExtra("updatedDayBought"));
                    cupboardItems.get(pos).setDayExpiry(data.getStringExtra("updatedDayExpiry"));
                    cupboardItems.get(pos).setAmountBought(Integer.valueOf(data.getStringExtra("updatedAmountBought")));
                    cupboardItems.get(pos).setAmountRemanining(Integer.valueOf(data.getStringExtra("updatedAmountRemaining")));
                     */
                }

                else
                {
                    Toast.makeText(activity, "You have just deleted " + cupboardItems.get(pos).getName(), Toast.LENGTH_SHORT).show();
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
                FoodCupboardItem newItem = data.getParcelableExtra("returnNewItem");
                cupboardItems.add(newItem);
                adapter.notifyDataSetChanged();
            }
        }
    }

}