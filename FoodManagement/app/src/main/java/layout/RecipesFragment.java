package layout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;

import com.example.cs15fmk.foodmanagement.AddNewFCItemMenu;
import com.example.cs15fmk.foodmanagement.MainActivity;
import com.example.cs15fmk.foodmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment {

    private Activity activity;

    SeekBar timescale;
    SearchView foodTypes;
    ListView ingredients;
    SearchView cuisineSearch;
    Button okay;

    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        timescale = (SeekBar) view.findViewById(R.id.timescale);
        foodTypes = (SearchView) view.findViewById(R.id.foodTypes);
        ingredients = (ListView) view.findViewById(R.id.ingredients);
        cuisineSearch = (SearchView) view.findViewById(R.id.cuisineSearch);
        okay = (Button) view.findViewById(R.id.okay);


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        return view;
    }

}
