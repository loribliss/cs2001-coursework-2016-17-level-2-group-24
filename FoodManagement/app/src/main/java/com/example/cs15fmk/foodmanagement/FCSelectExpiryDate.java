package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class FCSelectExpiryDate extends AppCompatActivity {

    private String expiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcselect_expiry_date);
        Button finishedSelectingDate = (Button)findViewById(R.id.finishCalendarDateSelection);

        DatePicker datePicker = (DatePicker)findViewById(R.id.datePickerFC);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //datePicker.setMinDate();
        //NEED TO GET THE DAY BOUGHT AND PASS THIS AS THE MINIMUM DATE



        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth)
            {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);

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
