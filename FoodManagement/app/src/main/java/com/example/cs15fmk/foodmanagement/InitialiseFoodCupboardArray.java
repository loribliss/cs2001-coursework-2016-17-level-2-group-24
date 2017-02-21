package com.example.cs15fmk.foodmanagement;

import java.util.ArrayList;

public class InitialiseFoodCupboardArray
{
    private static ArrayList<FoodCupboardItem> masterListFoodCupboardItems = new ArrayList<>();

    public static void initialiseArray()
    {
        masterListFoodCupboardItems.add(new FoodCupboardItem("Chicken", "31/01/2017", "07/02/2017", "8", "AMOUNT", "1", "1", "400", "300"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Bread", "21/01/2017", "08/02/2017", "6", "QUANTITY", "12", "6","0", "0"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Cereal", "11/01/2017", "09/02/2017", "30", "AMOUNT", "1","1", "750", "50"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Beef", "01/01/2017", "27/02/2017", "5", "QUANTITY&AMOUNT", "3","1","1000","450"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Milk", "05/01/2017", "10/03/2017", "5", "AMOUNT", "1","1","2000", "500"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Wholemeal Rolls", "15/12/2016", "15/03/2017", "2","QUANTITY","4","2","0","0"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Turkey rashers", "11/01/2017", "26/02/2017", "14", "AMOUNT", "1", "1","8","2"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Eggs", "18/02/2017", "01/03/2017", "16", "QUANTITY&AMOUNT", "2", "2", "12", "6"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Quorn", "01/01/2017", "28/02/2017", "2", "QUANTITY&AMOUNT", "3","1","1000","450"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Margarine", "01/01/2017", "24/02/2017", "3", "AMOUNT", "1","1","2000", "500"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("White Rolls", "23/01/2017", "03/03/2017", "2","QUANTITY","4","2","0","0"));
        masterListFoodCupboardItems.add(new FoodCupboardItem("Bacon", "07/02/2017", "10/03/2017", "14", "AMOUNT", "1", "1","8","2"));
    }
    public static ArrayList<FoodCupboardItem> getArray()
    {
        return masterListFoodCupboardItems;
    }
    public static void updateArray(ArrayList<FoodCupboardItem> updatedArray)
    {
        masterListFoodCupboardItems = updatedArray;
    }
}
