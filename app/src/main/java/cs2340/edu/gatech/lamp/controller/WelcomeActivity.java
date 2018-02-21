package cs2340.edu.gatech.lamp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import cs2340.edu.gatech.lamp.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth currAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currAuth = FirebaseAuth.getInstance();

        /*setContentView(R.layout.activity_welcome);

        Button login = findViewById(R.id.btn_goto_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });*/

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currUser = currAuth.getCurrentUser();
        if (currUser != null) {
            startActivity(new Intent(this, DefaultActivity.class));
        } else {
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build()))
                            .setIsSmartLockEnabled(false, true)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    /*private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));*/
    }
