package group24.materialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import group24.materialdesign.api.RecipeService;

public class LoginActivity extends AppCompatActivity {

    RecipeService service = new RecipeServiceTestImpl();

    EditText email;
    EditText password;
    Button submit;

    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_password);
        submit = (Button) findViewById(R.id.submit);
        error = (TextView) findViewById(R.id.error);
    }


    public void submit(View v) {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        // TODO validate input
        if (email.length() == 0) {
            this.error.setText("please enter email");
            return;
        }
        if (password.length() == 0) {
            this.error.setText("please enter password");
            return;
        }

        boolean success = service.login(email, password);
        if (success) {
            this.error.setText("SUCCESS");
        } else {
            this.error.setText("FAILURE");
        }
    }

}
