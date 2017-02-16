package com.example.cs15fmk.foodmanagement;

public class FoodCupboardItem {

    private String name;
    private String dayBought;
    private String dayExpiry;
    private String daysRemaining;
    private int amountBought;
    private int amountRemaining;
    private int progress;

    public FoodCupboardItem(String inputName, String inputDayBought, String inputDayExpiry, int inputAmountBought, int inputAmountRemaining)
    {
        name = inputName;
        dayBought = inputDayBought;
        dayExpiry = inputDayExpiry;
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
    public int getAmountBought()
    {
        return amountBought;
    }
    public int getAmountRemaining()
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
    public void setAmountBought(int newAmountBought)
    {
        amountBought = newAmountBought;
    }
    public void setAmountRemanining(int newAmountRemaining) {
        amountRemaining = newAmountRemaining;
    }
    public void setProgress(int inputProgress)
    {
        progress = inputProgress;
    }
    public int getProgress()
    {
        return progress;
    }

}
