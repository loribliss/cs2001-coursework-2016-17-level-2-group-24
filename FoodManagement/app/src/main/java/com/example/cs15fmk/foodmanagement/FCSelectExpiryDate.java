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

    private String dateBought;
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

        dateBought = getIntent().getStringExtra("boughtDate");
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy"); //MAY NEED TO BE 2017 AS LATER THE DATE IS RETURNED AS 2017 INSTEAD BY THE DATECHANGED METHOD
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

        expiryDate = getIntent().getStringExtra("expiryDate");
        //ADD IN CONVERSION OF THE CURRENT EXPIRY DATE ONTO THE CALENDAR SO TAHT IT STARTS ON THAT DATE
        //REMOVE / AND THEN CHECK FIRST AND THIRD CHARACTER, IF 0 THEN REMOVE AND THEN PARSE THE RESULT
        
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

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
