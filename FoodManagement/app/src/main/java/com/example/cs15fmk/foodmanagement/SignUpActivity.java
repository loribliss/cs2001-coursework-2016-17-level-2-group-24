package com.example.cs15fmk.foodmanagement;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity
implements  View.OnClickListener{
SqlLiteDatabase db;

    EditText editName, editEmail, editPassword;
    private static final String TAG = " gtd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //MAYBE THIS
        db = new SqlLiteDatabase(this);

        Button btnAddUser = (Button) findViewById(R.id.btn_signup);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goBack = new Intent(SignUpActivity.this, MainActivity.class);
                editName = (EditText) findViewById(R.id.input_name);
                editEmail = (EditText) findViewById(R.id.input_email);
                editPassword = (EditText) findViewById(R.id.input_password);
                db.addUser(editName.getText().toString(),
                        editEmail.getText().toString(),
                        editPassword.getText().toString());
                startActivity(goBack);
            }
        });
    }

    public void onClick(View v) {
        Intent goBack = new Intent(SignUpActivity.this, MainActivity.class);
        db = new SqlLiteDatabase(v.getContext());
        db.addUser(editName.getText().toString(),
                editEmail.getText().toString(),
                editPassword.getText().toString());
        startActivity(goBack);
        Log.d(TAG, "EM" + editEmail.getText().toString());
        Log.d(TAG, editPassword.getText().toString());


}



}
