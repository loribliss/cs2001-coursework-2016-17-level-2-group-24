package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OCRCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocrcheck);

        String inputText = getIntent().getStringExtra("input");

        EditText checkText = (EditText)findViewById(R.id.check_box);
        checkText.setText(inputText);

        Button finishButton = (Button)findViewById(R.id.finishCheck);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String finalTextInput = checkText.getText().toString();
                Intent data = new Intent();
                data.putExtra("finalText",finalTextInput);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
