package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Admin;
import cs2340.edu.gatech.lamp.model.HomelessUser;
import cs2340.edu.gatech.lamp.model.ShelterOwner;
import cs2340.edu.gatech.lamp.model.User;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.utils.HelperUI;

public class WelcomeActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth currAuth;

    private Context context = this;

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
            //this is the jank
            ShelterOwner suser = new ShelterOwner(currUser);
            Model.getInstance().setShelterOwnerDefault(suser);

            Model.getInstance().load(this);
            Model.getInstance().setCurrentUser(new HomelessUser(currUser));
            HelperUI.goToDefault(this);
        } else {
            setContentView(R.layout.activity_welcome);
            Button logIn = findViewById(R.id.btn_logIn);

            final EditText etUsername = findViewById(R.id.username);
            final EditText etPassword = findViewById(R.id.password);
            logIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currAuth.signInWithEmailAndPassword(etUsername.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("WelcomeActivity", "signInWithEmail:success");
                                        FirebaseUser user = currAuth.getCurrentUser();

                                        //this is the jank
                                        ShelterOwner suser = new ShelterOwner(user);
                                        Model.getInstance().setShelterOwnerDefault(suser);

                                        Model.getInstance().load(context);
                                        Model.getInstance().setCurrentUser(new HomelessUser(user));

                                        HelperUI.goToDefault(context);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("WelcomeActivity", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(context, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        HelperUI.goToWelcome(context);
                                    }
                                }
                            });
                }
            });

            Button register = findViewById(R.id.btn_register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HelperUI.goToRegister(context);
                }
            });
        }
    }
}
