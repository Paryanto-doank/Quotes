package mrdoank.blogspot.co.id.quotes.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import mrdoank.blogspot.co.id.quotes.MainActivity;
import mrdoank.blogspot.co.id.quotes.R;
import mrdoank.blogspot.co.id.quotes.model.Person;
import mrdoank.blogspot.co.id.quotes.utility.DatabaseHandler;

/**
 * Created by root on 03/12/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPass;
    private final String TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Register");

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void openLogin(View view) {
        finish();
    }

    public void doRegister(View view) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        Person person = new Person(name, email, pass);
        Log.d(TAG, "doRegister: " + person.toString());
        databaseHandler.addUser(person);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
