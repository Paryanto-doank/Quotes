package mrdoank.blogspot.co.id.quotes.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mrdoank.blogspot.co.id.quotes.MainActivity;
import mrdoank.blogspot.co.id.quotes.R;
import mrdoank.blogspot.co.id.quotes.model.Person;
import mrdoank.blogspot.co.id.quotes.utility.DatabaseHandler;
import mrdoank.blogspot.co.id.quotes.utility.SessionManager;

/**
 * Created by root on 03/12/17.
 */

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();
    private EditText etEmail, etPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);

        if (SessionManager.getInstance().isLogin()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        for (Person person : DatabaseHandler.getInstance().getAllUser()) {
            Log.d(TAG, "onCreate: " + person.toString());
        }

        setTitle("Login");

    }

    public void openRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void doLogin(View view) {

        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Email can't be blank !");
            etEmail.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            etPass.setError("Password can't be blank !");
            etPass.requestFocus();
            return;
        }

        if (DatabaseHandler.getInstance().checkUser(email, pass)) {
            SessionManager.getInstance().setLogin(true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else Toast.makeText(this, "Email Or Pass Invalid !!! ", Toast.LENGTH_SHORT).show();
    }
}
