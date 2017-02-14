package com.example.cs15fmk.foodmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.cs15fmk.foodmanagement.R.id.btn_signup;

public class SignUpActivity extends AppCompatActivity {

    EditText editName, editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btn = (Button) findViewById(btn_signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(SignUpActivity.this,
                        "You have signed up", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
