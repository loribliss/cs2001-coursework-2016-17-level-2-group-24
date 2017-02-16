package com.example.cs15fmk.foodmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.cs15fmk.foodmanagement.R.id.btn_signup;
import static com.example.cs15fmk.foodmanagement.R.id.signOut;

public class SignUpActivity extends AppCompatActivity {
private Mysql mysql = new Mysql();
    EditText editName, editEmail, editPassword;
    ViewSwitcher simpleViewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         simpleViewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher); // get the reference of ViewSwitcher
        Button btn = (Button) findViewById(btn_signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(SignUpActivity.this,
                        "You have signed up", Toast.LENGTH_SHORT).show();
/*simpleViewSwitcher.showNext();*/
                String[] args = {};
                Mysql.main(args);
            }
        });
    }
    public void onClickSignOut(){
       /* simpleViewSwitcher.showPrevious();*/
    }


}
