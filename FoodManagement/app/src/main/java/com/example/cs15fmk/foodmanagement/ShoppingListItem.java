package com.example.cs15fmk.foodmanagement;
<<<<<<< HEAD


import android.os.Parcel;
import android.os.Parcelable;

//This class is the template for shopping list items which will hold values for a name, priority and a quantity (if showing as amount, this will be updated)
//It also implements parcelable to allow for easy transferrance of the shopping list objects between activities
=======
import android.os.Parcel;
import android.os.Parcelable;

//This class is the template for shopping list items which will hold values for a name, quantity, priority and a date created (for sorting purposes)
//Methods are standard get and set methods - however, some may be removed (e.g. modification of date created) as they will be initialised automatically when the objet is created
// It also implements parcelable to allow for easy transferrance of the shopping list objects between activities
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5

public class ShoppingListItem implements Parcelable{

    private String name;
<<<<<<< HEAD
    private String amount;
    private String priority;
    private boolean checkboxState = false;

    public ShoppingListItem(String inputName, String inputAmount, String inputPriority, Boolean inputCheckBoxState)
    {
        name = inputName;
        amount = inputAmount;
        priority = inputPriority;
=======
    private String quantity;
    private String priority;
    private String dateCreated;
    private boolean checkboxState = false;

    public ShoppingListItem(String inputName, String inputQuantity, String inputPriority, String inputDateCreated, Boolean inputCheckBoxState)
    {
        name = inputName;
        quantity = inputQuantity;
        priority = inputPriority;
        dateCreated = inputDateCreated;
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
        checkboxState = inputCheckBoxState;
    }

    public String getItemName()
    {
        return name;
    }
<<<<<<< HEAD
    public String getItemAmount()
    {
        return amount;
=======
    public String getItemQuantity()
    {
        return quantity;
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
    }
    public String getItemPriority()
    {
        return priority;
    }
<<<<<<< HEAD
    public boolean getCheckBoxState() { return checkboxState;}
=======
    public String getItemDateCreated() {return dateCreated;}
    public boolean getItemCheckBoxState() { return checkboxState;}
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5

    public void setItemName(String setName)
    {
        name = setName;
    }
<<<<<<< HEAD
    public void setItemAmount(String setAmount)
    {
        amount = setAmount;
=======
    public void setItemQuantity(String setQuantity)
    {
        quantity = setQuantity;
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
    }
    public void setItemPrioirty(String setPrioirty)
    {
        priority = setPrioirty;
    }
<<<<<<< HEAD
    public void setCheckboxState(boolean state) {checkboxState = state;}
=======
    public void setItemDateCreated(String setDateCreated) {dateCreated = setDateCreated;}
    public void setItemCheckboxState(boolean state) {checkboxState = state;}
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5

    //parcel part
    public ShoppingListItem(Parcel in)
    {
<<<<<<< HEAD
        String[] data= new String[3];
        in.readStringArray(data);
        name= data[0];
        amount= data[1];
        priority = data[2];
=======
        String[] data= new String[4];
        in.readStringArray(data);
        name = data[0];
        quantity = data[1];
        priority = data[2];
        dateCreated = data[3];
        //Note that order of variable writing and reading is important
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
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
<<<<<<< HEAD
        dest.writeStringArray(new String[]{name,amount,priority});
=======
        dest.writeStringArray(new String[]{name,quantity,priority,dateCreated});
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
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

<<<<<<< HEAD
}
=======
}
>>>>>>> a112afbb8d9866bc614720ebaa9e8af554c591c5
