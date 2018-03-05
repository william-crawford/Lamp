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
import cs2340.edu.gatech.lamp.model.Admin;
import cs2340.edu.gatech.lamp.model.User;
import cs2340.edu.gatech.lamp.utils.HelperUI;

public class WelcomeActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth currAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currUser = currAuth.getCurrentUser();
        if (currUser != null) {
            User appUser = new Admin(currUser);
            appUser.writeNewUser();
            HelperUI.goToDefault(this);
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
}
