package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs15fmk.foodmanagement.AddShoppingItem;
import com.example.cs15fmk.foodmanagement.EditShoppingItem;
import com.example.cs15fmk.foodmanagement.InitialiseShoppingListArray;
import com.example.cs15fmk.foodmanagement.R;
import com.example.cs15fmk.foodmanagement.SItemAdapter;
import com.example.cs15fmk.foodmanagement.ShoppingListItem;
import com.example.cs15fmk.foodmanagement.SortingAlgorithmsShoppingList;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.cs15fmk.foodmanagement.R.id.spinner;

public class ShoppingListFragment extends Fragment {

    private ArrayList<ShoppingListItem> shoppingItems = new ArrayList<>();
    private SItemAdapter adapter;
    private ListView listView;
    private Activity activity;
    private Spinner spinner;
    private static final String[]optionsShoppingListSpinner = {"All", "Alphabetical", "Quantity", "Priority"}; //ADD MOST RECENT
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        shoppingItems = InitialiseShoppingListArray.getArray();

        activity = getActivity();
        adapter = new SItemAdapter (activity, shoppingItems);
        listView = (ListView) view.findViewById(R.id.shoppinglist);
        listView.setAdapter(adapter);

        FloatingActionButton addNewItem = (FloatingActionButton) view.findViewById(R.id.add_new_shopping_item);
        addNewItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(activity, AddShoppingItem.class);
                startActivityForResult(intent, 1);
            }
        }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(activity, "Clicked on Row: " + position, Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() //EDITING ANY VIEW
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                editItemEntry(position);
                return true;
            }
        });
        spinner = (Spinner) view.findViewById(R.id.sortByShoppingListSpinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item,optionsShoppingListSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 0)
                {   //does not go to default...have default as most recent
                    shoppingItems = InitialiseShoppingListArray.getArray();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(activity, "You have just selected " + optionsShoppingListSpinner[position], Toast.LENGTH_SHORT).show();
                }
                if (position == 1)
                {
                    shoppingItems = SortingAlgorithmsShoppingList.sortName(shoppingItems);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(activity, "You have just selected " + optionsShoppingListSpinner[position], Toast.LENGTH_SHORT).show();
                }
                else if (position ==2)
                {
                    shoppingItems = SortingAlgorithmsShoppingList.sortQuantity(shoppingItems);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(activity, "You have just selected " + optionsShoppingListSpinner[position], Toast.LENGTH_SHORT).show();
                }
                else if (position ==3)
                {
                    shoppingItems = SortingAlgorithmsShoppingList.sortPriority(shoppingItems);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(activity, "You have just selected " + optionsShoppingListSpinner[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //empty
            }
        });

        return view;
    }

    public void editItemEntry(int position)
    {
        ShoppingListItem item = shoppingItems.get(position);

        Intent intent = new Intent(activity, EditShoppingItem.class);
        //intent.putExtra("Current name", item.getItemName());
        //intent.putExtra("Current amount", item.getItemQuantity());
        //intent.putExtra("Current priority", item.getItemPriority());
        //intent.putExtra("Date created", item.getItemDateCreated());
        intent.putExtra("Position", String.valueOf(position));
        intent.putExtra("editItem", item); //parceable object with features except boolean checkbox state
        startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK) {

                ShoppingListItem a = data.getParcelableExtra("completeAddItem");
                String name = a.getItemName();
                String quantity = a.getItemQuantity();
                String priority = a.getItemPriority();
                String dateCreated = a.getItemDateCreated();
                boolean state = false;
                //String name = data.getStringExtra("name");
                //String amount = data.getStringExtra("amount");
                //String priority = data.getStringExtra("priority");
                shoppingItems.add(new ShoppingListItem(name, quantity, priority, dateCreated, state));
                adapter.notifyDataSetChanged();
                editMasterList();
            }
        }

        if (requestCode ==2)
        {
            if (resultCode == RESULT_OK)
            {
                String position = data.getStringExtra("position");
                int pos = Integer.valueOf(position);
                if (data.getStringExtra("userOption").equals("edit"))
                {
                    ShoppingListItem a = data.getParcelableExtra("completeEditItem");
                    String newName = a.getItemName();
                    String newAmount = a.getItemQuantity();
                    String newPriority = a.getItemPriority();
                    String newDateCreated = a.getItemDateCreated();

                    //CONSIDER AN UPDATE METHOD
                    //String newName = data.getStringExtra("newName");
                    //String newAmount = data.getStringExtra("newAmount");
                    //String newPriority = data.getStringExtra("newPriority");

                    ShoppingListItem edit = shoppingItems.get(pos);
                    edit.setItemName(newName);
                    edit.setItemQuantity(newAmount);
                    edit.setItemPrioirty(newPriority);
                    edit.setItemDateCreated(newDateCreated);
                    adapter.notifyDataSetChanged();
                    editMasterList();
                }
                else
                {
                    Toast.makeText(activity, "You have just deleted " + shoppingItems.get(pos).getItemName(), Toast.LENGTH_SHORT).show();
                    shoppingItems.remove(pos);
                    adapter.notifyDataSetChanged(); //deletion does not change anything permanently if you exit the screen
                    editMasterList();
                }
            }
        }
    }
    private void editMasterList()
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                InitialiseShoppingListArray.updateArray(shoppingItems);
            }
        });
    }

}