package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddNewFCItemMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fcitem_menu);

        Button addItemManual = (Button) findViewById(R.id.add_FC_manual);
        addItemManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddNewFCItemMenu.this, AddFoodCupboardItemManual.class);
                startActivityForResult(intent, 1);
            }
        } );

        Button addItemPhoto = (Button) findViewById(R.id.add_FC_photo);
        addItemPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        } );
        Button addItemScan = (Button) findViewById(R.id.add_FC_scan);
        addItemPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        } );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                Intent datas = new Intent();
                FoodCupboardItem newItem = data.getParcelableExtra("newItem");
                datas.putExtra("returnNewItem", newItem);
                //datas.putExtra("newFCName", data.getStringExtra("new_FC_Name"));
                //datas.putExtra("newFCDayBought",data.getStringExtra("new_FC_DayBought"));
                //datas.putExtra("newFCDayExpiry",data.getStringExtra("new_FC_DayExpiry"));
                //datas.putExtra("newFCAmountBought",data.getStringExtra("new_FC_AmountBought"));
                setResult(RESULT_OK, datas);
                finish();

            }
        }
    }
}
