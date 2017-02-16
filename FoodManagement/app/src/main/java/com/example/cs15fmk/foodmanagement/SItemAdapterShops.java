package com.example.cs15fmk.foodmanagement;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cs15fmk.foodmanagement.R;

import java.util.ArrayList;

public class SItemAdapterShops extends RecyclerView.Adapter<SItemAdapterShops.ViewHolder> {
    private ArrayList<String> countries;

    public SItemAdapterShops(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public SItemAdapterShops.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.map_slide_up_panel, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SItemAdapterShops.ViewHolder viewHolder, int i) {

        viewHolder.tv_country.setText(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_country;

        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView) view.findViewById(R.id.distance);
        }
    }
}
 