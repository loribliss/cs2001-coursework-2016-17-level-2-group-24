package com.example.cs15fmk.foodmanagement;

import java.util.ArrayList;

public class InitialiseShoppingListArray
{

    private static ArrayList<ShoppingListItem> masterListShoppingItems = new ArrayList<>();

    public static void initialiseArray()
    {
        masterListShoppingItems.add(new ShoppingListItem("Milk", "2", "High", "16/02/17", false));
        masterListShoppingItems.add(new ShoppingListItem("Chicken fillets", "1", "Medium", "16/02/17", false));
        masterListShoppingItems.add(new ShoppingListItem("Bran Flakes", "3", "Low", "16/02/17", false));
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
