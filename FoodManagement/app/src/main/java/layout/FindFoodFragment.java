package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs15fmk.foodmanagement.Main2Activity;
import com.example.cs15fmk.foodmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFoodFragment extends Fragment {


    public FindFoodFragment() {
       //DOESNT WORK  getActivity().getActionBar().setTitle("Find food");
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Main2Activity)getActivity()).setActionBarTitle("Find Food");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_food, container, false);

    }

}
