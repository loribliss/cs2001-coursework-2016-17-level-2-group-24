package com.example.cs15fmk.foodmanagement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by simsohi on 18/02/17.
 */

public class IngredientItem {

    private Bundle bundle;

    public IngredientItem(final Bundle bundle) {
        this.bundle = bundle;
    }

    public int getId(){
        return bundle.getInt("id");
    }

    public void setId(final int id) {
        bundle.putInt("id", id);
    }

    public String getName() {
        return bundle.getString("name");
    }

    public void setName(final String name) {
        bundle.putString("name", name);
    }

    public String getUnit() {
        return bundle.getString("unit");
    }

    public void setUnit(final String unit) {
        bundle.putString("unit", unit);
    }

    public int getFoodType(){
        return bundle.getInt("food_type");
    }

    public void setFoodType(final int id) {
        bundle.putInt("food_type", id);
    }

    public Bundle toBundle() {
        return bundle;
    }
}
