package com.example.cs15fmk.foodmanagement;
import android.os.Parcel;
import android.os.Parcelable;

//This class is the template for shopping list items which will hold values for a name, quantity, priority and a date created (for sorting purposes)
//Methods are standard get and set methods - however, some may be removed (e.g. modification of date created) as they will be initialised automatically when the objet is created
// It also implements parcelable to allow for easy transferrance of the shopping list objects between activities

public class ShoppingListItem implements Parcelable{

    private String name;
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
        checkboxState = inputCheckBoxState;
    }

    public String getItemName()
    {
        return name;
    }
    public String getItemQuantity()
    {
        return quantity;
    }
    public String getItemPriority()
    {
        return priority;
    }
    public String getItemDateCreated() {return dateCreated;}
    public boolean getItemCheckBoxState() { return checkboxState;}

    public void setItemName(String setName)
    {
        name = setName;
    }
    public void setItemQuantity(String setQuantity)
    {
        quantity = setQuantity;
    }
    public void setItemPrioirty(String setPrioirty)
    {
        priority = setPrioirty;
    }
    public void setItemDateCreated(String setDateCreated) {dateCreated = setDateCreated;}
    public void setItemCheckboxState(boolean state) {checkboxState = state;}

    //parcel part
    public ShoppingListItem(Parcel in)
    {
        String[] data= new String[4];
        in.readStringArray(data);
        name = data[0];
        quantity = data[1];
        priority = data[2];
        dateCreated = data[3];
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
        dest.writeStringArray(new String[]{name,quantity,priority,dateCreated});
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