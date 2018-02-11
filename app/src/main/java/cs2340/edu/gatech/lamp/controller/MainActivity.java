package cs2340.edu.gatech.lamp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.LoginValidator;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyApp", "Started");
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);

        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        Button register = findViewById(R.id.btn_register);

    }

    private void login() {
        LoginValidator.loginResult result = LoginValidator.processLogin(
                username.getText().toString(),
                password.getText().toString()
        );

        switch (result) {
            case ADMIN:
                //doAdminThings
                break;
            case SHELTER:
                //shelterThings
                break;
            case HOMELESS:
                //homeless
                break;
            case DENIED:
                //display error
        }
    }

}
