package com.example.cs15fmk.foodmanagement;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodCupboardItem implements Parcelable{

    private String name;
    private String dayBought;
    private String dayExpiry;
    private String daysRemaining;
    private String userInputType;
    private String quantityBought;
    private String quantityRemaining;
    private String amountBought;
    private String amountRemaining;

    public FoodCupboardItem(String inputName, String inputDayBought, String inputDayExpiry,String inputDaysRemaining, String inputUserInputType,
                            String inputQuantityBought, String inputQuantityRemaining, String inputAmountBought, String inputAmountRemaining)
    {
        name = inputName;
        dayBought = inputDayBought;
        dayExpiry = inputDayExpiry;
        daysRemaining = inputDaysRemaining;
        userInputType = inputUserInputType; //quantity, amount or both
        quantityBought = inputQuantityBought;
        quantityRemaining = inputQuantityRemaining;
        amountBought = inputAmountBought;
        amountRemaining = inputAmountRemaining;
    }

    public String getName()
    {
        return name;
    }
    public String getDayBought()
    {
        return dayBought;
    }
    public String getDayExpiry()
    {
        return dayExpiry;
    }
    public String getDaysRemaining()
    {
        return daysRemaining;
    }
    public String getUserInputType()
    {
        return userInputType;
    }
    public String getQuantityBought()
    {
        return quantityBought;
    }
    public String getQuantityRemaining()
    {
        return quantityRemaining;
    }
    public String getAmountBought()
    {
        return amountBought;
    }
    public String getAmountRemaining()
    {
        return amountRemaining;
    }

    public void setName(String newName)
    {
        name = newName;
    }
    public void setDayBought(String newDayBought)
    {
        dayBought = newDayBought;
    }
    public void setDayExpiry(String newDayExpiry)
    {
        dayExpiry = newDayExpiry;
    }
    public void setDaysRemaining(String newDaysRemaining)
    {
        daysRemaining = newDaysRemaining;
    }
    public void setUserInputType(String newUserInputType)
    {
        userInputType = newUserInputType;
    }
    public void setQuantityBought(String newQuantityBought)
    {
        quantityBought = newQuantityBought;
    }
    public void setQuantityRemaining(String newQuantityRemaining)
    {
        quantityRemaining = newQuantityRemaining;
    }
    public void setAmountBought(String newAmountBought)
    {
        amountBought = newAmountBought;
    }
    public void setAmountRemanining(String newAmountRemaining)
    {
        amountRemaining = newAmountRemaining;
    }


    //parcel part
    public FoodCupboardItem(Parcel in)
    {
        String[] data= new String[9];
        in.readStringArray(data);
        name = data[0];
        dayBought = data[1];
        dayExpiry = data[2];
        daysRemaining = data[3];
        userInputType = data[4];
        quantityBought = data[5];
        quantityRemaining = data[6];
        amountBought = data[7];
        amountRemaining = data[8];
        //Note that order of variable writing and reading is important
    }
    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeStringArray(new String[]{name,dayBought,dayExpiry,daysRemaining,userInputType,quantityBought,quantityRemaining,amountBought,amountRemaining});
    }

    public static final Parcelable.Creator<FoodCupboardItem> CREATOR= new Parcelable.Creator<FoodCupboardItem>() {

        @Override
        public FoodCupboardItem createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new FoodCupboardItem(source);  //using parcelable constructor
        }

        @Override
        public FoodCupboardItem[] newArray(int size) {
            // TODO Auto-generated method stub
            return new FoodCupboardItem[size];
        }
    };

}
