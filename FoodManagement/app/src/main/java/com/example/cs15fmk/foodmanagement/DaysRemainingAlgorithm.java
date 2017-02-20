package com.example.cs15fmk.foodmanagement;

public class DaysRemainingAlgorithm {

    //WHOLE THING ABOUT INCLUDING THE END DATE AND IS MISSING LEAP YEAR CODE
    //ALSO ASSUMES DATE IS ALREADY IN CORRECT FORMAT AND HAS BEEN CHECKED APPROPRIATELY

    private static int[] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};

    public static int getDaysRemaining (String dateOne, String dateTwo)
    {
        String newDateOne;
        String newDateTwo;

        newDateOne = removeSlashes(dateOne);
        newDateTwo = removeSlashes(dateTwo);

        int dayOne = dayNumber(newDateOne);
        int monthOne = monthNumber(newDateOne);
        int yearOne = yearNumber(newDateOne);

        int dayTwo = dayNumber(newDateTwo);
        int monthTwo = monthNumber(newDateTwo);
        int yearTwo = yearNumber(newDateTwo);

        if (yearTwo - yearOne == 0)
        {
            int daysElapsedDateOne = daysElapsed(dayOne,monthOne);
            int daysElapsedDateTwo = daysElapsed(dayTwo,monthTwo);

            int daysRemaining = daysElapsedDateTwo - daysElapsedDateOne;
            return daysRemaining;
        }
        else //not including leapYears - more complex!
        {
            int yearDifference = yearTwo - yearOne;
            int daysElapsedDateOne = daysElapsed(dayOne, monthOne);
            int daysElapsedDateTwo = daysElapsed(dayTwo, monthTwo) + (yearDifference * 365);

            int daysRemaining = daysElapsedDateTwo - daysElapsedDateOne;
            return daysRemaining;
        }
    }

    private static String removeSlashes(String a)
    {
        return a.substring(0, 2) + a.substring(3, 5) + a.substring(6, a.length());
    }
    private static int dayNumber(String newDate)
    {
        int day;
        if (newDate.charAt(0) == '0')
        {
            day = Integer.parseInt(newDate.substring(1,2));
        }
        else
        {
            day = Integer.parseInt(newDate.substring(0, 2));
        }
        return day;
    }
    private static int monthNumber(String newDate)
    {
        int month;
        if (newDate.charAt(2) == '0')
        {
            month = Integer.parseInt(newDate.substring(3,4));
        }
        else
        {
            month = Integer.parseInt(newDate.substring(2, 4));
        }

        return month;
    }
    private static int yearNumber(String newDate)
    {
        return Integer.parseInt(newDate.substring(4, newDate.length()));
    }
    private static int daysElapsed(int day, int month)
    {
        int daysElapsed =0;

        if (month>1)
        {
            for (int i=0;i<month-1;i++)
            {
                daysElapsed += daysInMonths[i];
            }
        }
        return daysElapsed + day;
    }
}

