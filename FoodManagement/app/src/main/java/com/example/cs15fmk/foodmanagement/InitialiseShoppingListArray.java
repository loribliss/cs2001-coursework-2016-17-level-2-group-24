package com.example.cs15fmk.foodmanagement;

import java.util.ArrayList;

public class InitialiseShoppingListArray
{
    private static ArrayList<ShoppingListItem> masterListShoppingItems = new ArrayList<>();

    public static void initialiseArray()
    {
        masterListShoppingItems.add(new ShoppingListItem("Milk", "2", "High", "06/02/2017", false));
        masterListShoppingItems.add(new ShoppingListItem("Chicken fillets", "1", "Medium", "18/01/2017", false));
        masterListShoppingItems.add(new ShoppingListItem("Bran Flakes", "3", "Low", "13/02/2017", false));
        masterListShoppingItems.add(new ShoppingListItem("Bread", "1", "High", "21/02/2017", false));
        masterListShoppingItems.add(new ShoppingListItem("Turkey", "3", "Medium", "19/02/2016", false));
        masterListShoppingItems.add(new ShoppingListItem("Eggs", "12", "Medium", "19/03/2016", false));
    }

    public static ArrayList<ShoppingListItem> getArray()
    {
        return masterListShoppingItems;
    }

    public static void updateArray(ArrayList<ShoppingListItem> updatedArray)
    {
        masterListShoppingItems = updatedArray;
    }
}
