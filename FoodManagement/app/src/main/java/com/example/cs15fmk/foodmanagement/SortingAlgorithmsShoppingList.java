package com.example.cs15fmk.foodmanagement;

import java.util.ArrayList;

public class SortingAlgorithmsShoppingList
{
    public static ArrayList<ShoppingListItem> sortName(ArrayList<ShoppingListItem> a)
    {//bubble sort on alphabetical name
        boolean flag = true;
        ShoppingListItem temp;

        while(flag)
        {
            flag = false;
            for (int i=0; i<a.size()-1;i++)
            {
                ShoppingListItem one = a.get(i);
                ShoppingListItem two = a.get(i+1);

                if (one.getItemName().compareToIgnoreCase(two.getItemName())>0)
                {
                    temp = one;
                    a.set(i, two);
                    a.set(i+1,temp);
                    flag = true;
                }
            }
        }
        return a;
    }
    public static ArrayList<ShoppingListItem> sortQuantity(ArrayList<ShoppingListItem> a)
    {//bubble sort for quantity (number field)
        boolean flag = true;
        ShoppingListItem temp;

        while(flag)
        {
            flag = false;
            for (int i=0; i<a.size()-1;i++)
            {
                ShoppingListItem one = a.get(i);
                ShoppingListItem two = a.get(i+1);

                if (one.getIntItemQuantity() > two.getIntItemQuantity())
                {
                    temp = one;
                    a.set(i, two);
                    a.set(i+1,temp);
                    flag = true;
                }
            }
        }
        return a;
    }
    public static ArrayList<ShoppingListItem> sortPriority(ArrayList<ShoppingListItem> a)
    {//bubble sort on priority - no internal sorting (could include???)
        boolean flag = true;
        ShoppingListItem temp;

        while(flag)
        {
            flag = false;
            for (int i=0; i<a.size()-1;i++)
            {
                ShoppingListItem one = a.get(i);
                ShoppingListItem two = a.get(i+1);

                if (one.getItemPriority().compareToIgnoreCase(two.getItemPriority())>0)
                {
                    temp = one;
                    a.set(i, two);
                    a.set(i+1,temp);
                    flag = true;
                }
            }
        }
        return a;
    }
}
