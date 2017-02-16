package com.example.cs15fmk.foodmanagement;


import android.os.Parcel;
import android.os.Parcelable;

//This class is the template for shopping list items which will hold values for a name, priority and a quantity (if showing as amount, this will be updated)
//It also implements parcelable to allow for easy transferrance of the shopping list objects between activities

public class ShoppingListItem implements Parcelable{

    private String name;
    private String amount;
    private String priority;
    private boolean checkboxState = false;

    public ShoppingListItem(String inputName, String inputAmount, String inputPriority, Boolean inputCheckBoxState)
    {
        name = inputName;
        amount = inputAmount;
        priority = inputPriority;
        checkboxState = inputCheckBoxState;
    }

    public String getItemName()
    {
        return name;
    }
    public String getItemAmount()
    {
        return amount;
    }
    public String getItemPriority()
    {
        return priority;
    }
    public boolean getCheckBoxState() { return checkboxState;}

    public void setItemName(String setName)
    {
        name = setName;
    }
    public void setItemAmount(String setAmount)
    {
        amount = setAmount;
    }
    public void setItemPrioirty(String setPrioirty)
    {
        priority = setPrioirty;
    }
    public void setCheckboxState(boolean state) {checkboxState = state;}

    //parcel part
    public ShoppingListItem(Parcel in)
    {
        String[] data= new String[3];
        in.readStringArray(data);
        name= data[0];
        amount= data[1];
        priority = data[2];
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
        dest.writeStringArray(new String[]{name,amount,priority});
    }

    public static final Parcelable.Creator<ShoppingListItem> CREATOR= new Parcelable.Creator<ShoppingListItem>() {

        @Override
        public ShoppingListItem createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new ShoppingListItem(source);  //using parcelable constructor
        }

        @Override
        public ShoppingListItem[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ShoppingListItem[size];
        }
    };

}