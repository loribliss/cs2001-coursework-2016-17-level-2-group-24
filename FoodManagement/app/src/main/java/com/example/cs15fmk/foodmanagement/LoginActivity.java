package com.example.cs15fmk.foodmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void onClick(View v) {
        Toast.makeText(LoginActivity.this,
                "You have signed in", Toast.LENGTH_SHORT).show();
    }
}
