package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FCSelectExpiryDate extends AppCompatActivity {

    private String expiryDate;
    private long milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcselect_expiry_date);
        Button finishedSelectingDate = (Button)findViewById(R.id.finishCalendarDateSelection);
        DatePicker datePicker = (DatePicker)findViewById(R.id.datePickerFC);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //NEED TO GET THE DAY BOUGHT AND PASS THIS AS THE MINIMUM DATE
        String dateBought = getIntent().getStringExtra("boughtDate");
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); //MAY NEED TO BE 2017 AS LATER THE DATE IS RETURNED AS 2017 INSTEAD BY THE DATECHANGED METHOD
        try
        {
            Date d = f.parse(dateBought);
            milliseconds = d.getTime();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        datePicker.setMinDate(milliseconds);

        if (getIntent().getStringExtra("requestType").equals("EDIT"))
        {
            expiryDate = getIntent().getStringExtra("expiryDate"); //edit keeps current expiry date if finished pressed and
            //CONVERSION OF THE CURRENT EXPIRY DATE ONTO THE CALENDAR SO TAHT IT STARTS ON THAT DATE

            String newDate = "";

            newDate = expiryDate.substring(0, 2) + expiryDate.substring(3, 5) + expiryDate.substring(6, expiryDate.length());

            int day;
            int month;
            int year;

            if (newDate.charAt(0) == '0')
            {
                day = Integer.parseInt(newDate.substring(1,2));
            }
            else
            {
                day = Integer.parseInt(newDate.substring(0, 2));
            }
            System.out.println(day);

            if (newDate.charAt(2) == '0')
            {
                month = Integer.parseInt(newDate.substring(3,4));
            }
            else
            {
                month = Integer.parseInt(newDate.substring(2, 4));
            }
            year = Integer.parseInt(newDate.substring(4, newDate.length()));


            //old init method datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            datePicker.init(year, month-1, day, new DatePicker.OnDateChangedListener() {

                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth)
                {
                    if (dayOfMonth <10)
                    {
                        expiryDate = "0" + dayOfMonth + "/";
                    }
                    else
                    {
                        expiryDate = dayOfMonth + "/";
                    }

                    if (month < 9)
                    {
                        month+=1;
                        expiryDate += "0" + month + "/" + year;
                    }
                    else
                    {
                        expiryDate += month+1 + "/" + year;
                    }
                    finishedSelectingDate.setText(expiryDate);
                }
            });
        }

        else if (getIntent().getStringExtra("requestType").equals("NEW"))
        {
            expiryDate=dateBought; //NEEDS CHANGING set expiry date with current day if finish pressed without selecting a new date (could use toast message instead)

            datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() { //set first date today's date
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth)
                {
                    if (dayOfMonth <10)
                    {
                        expiryDate = "0" + dayOfMonth + "/";
                    }
                    else
                    {
                        expiryDate = dayOfMonth + "/";
                    }

                    if (month < 9)
                    {
                        month+=1;
                        expiryDate += "0" + month + "/" + year;
                    }
                    else
                    {
                        expiryDate += month+1 + "/" + year;
                    }
                    finishedSelectingDate.setText(expiryDate);
                }
            });

        }

        finishedSelectingDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent data = new Intent();
                data.putExtra("newExpiryDate", expiryDate);
                setResult(RESULT_OK, data);
                finish();
            }
        }
        );

    }
}
