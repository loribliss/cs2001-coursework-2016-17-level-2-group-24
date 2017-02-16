package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cs15fmk.foodmanagement.AddShoppingItem;
import com.example.cs15fmk.foodmanagement.EditShoppingItem;
import com.example.cs15fmk.foodmanagement.R;
import com.example.cs15fmk.foodmanagement.SItemAdapter;
import com.example.cs15fmk.foodmanagement.ShoppingListItem;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ShoppingListFragment extends Fragment {

    private final ArrayList<ShoppingListItem> shoppingItems = new ArrayList<>();
    private SItemAdapter adapter;
    private ListView listView;
    private Activity activity;

    //Changed Protected to public
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        activity = getActivity();


        shoppingItems.add(new ShoppingListItem("Grapes", "500g", "High", false));
        shoppingItems.add(new ShoppingListItem("Chicken", "650g", "Medium", false));
        shoppingItems.add(new ShoppingListItem("Bran Flakes", "1kg", "Low", false));

        adapter = new SItemAdapter (activity, shoppingItems);
        listView = (ListView) v.findViewById(R.id.shoppinglist);
        listView.setAdapter(adapter);

        FloatingActionButton addNewItem = (FloatingActionButton)v.findViewById(R.id.add_new_shopping_item);
        addNewItem.setOnClickListener(new View.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View view)
                                          {
                                              Intent intent = new Intent(activity , AddShoppingItem.class);
                                              startActivityForResult(intent, 1);
                                          }
                                      }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //ShoppingListItem a = shoppingItems.get(position);
                //a.setCheckboxState(true);
                Toast.makeText(activity, "Clicked on Row: " + position, Toast.LENGTH_SHORT).show();
                //adapter.notifyDataSetChanged();

                /*CheckBox check = (CheckBox) view.findViewById(R.id.checkbox);
                if (check.isChecked())
                {
                    Toast.makeText(ShoppingList.this, "now it is checked", Toast.LENGTH_SHORT).show();
                } */
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
        return v;
    }

    public void editItemEntry(int position)
    {
        ShoppingListItem item = shoppingItems.get(position);

        Intent intent = new Intent(activity, EditShoppingItem.class);
        //intent.putExtra("Current name", item.getItemName());
        //intent.putExtra("Current amount", item.getItemAmount());
        //intent.putExtra("Current priority", item.getItemPriority());
        intent.putExtra("Position", String.valueOf(position));
        intent.putExtra("editItem", item);
        startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK) {

                ShoppingListItem a = data.getParcelableExtra("completeAddItem");
                String name = a.getItemName();
                String amount = a.getItemAmount();
                String priority = a.getItemPriority();
                boolean state = a.getCheckBoxState();
                //String name = data.getStringExtra("name");
                //String amount = data.getStringExtra("amount");
                //String priority = data.getStringExtra("priority");
                shoppingItems.add(new ShoppingListItem(name, amount, priority,state));
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode ==2)
        {
            if (resultCode == RESULT_OK)
            {
                ShoppingListItem a = data.getParcelableExtra("completeEditItem");
                String newName = a.getItemName();
                String newAmount = a.getItemAmount();
                String newPriority = a.getItemPriority();
                //CONSIDER AN UPDATE METHOD

                //String newName = data.getStringExtra("newName");
                //String newAmount = data.getStringExtra("newAmount");
                //String newPriority = data.getStringExtra("newPriority");

                String position = data.getStringExtra("position");

                ShoppingListItem edit = shoppingItems.get(Integer.valueOf(position));
                edit.setItemName(newName);
                edit.setItemAmount(newAmount);
                edit.setItemPrioirty(newPriority);
                adapter.notifyDataSetChanged();
            }
        }
    }

}