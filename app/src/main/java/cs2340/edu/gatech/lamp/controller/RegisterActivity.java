package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.HomelessUser;
//import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.ShelterOwner;
import cs2340.edu.gatech.lamp.model.User;
import cs2340.edu.gatech.lamp.utils.HelperUI;

public class RegisterActivity extends AppCompatActivity {
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
        final FirebaseUser currUser = currAuth.getCurrentUser();
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.btn_comp_register);
        final EditText etEmail = findViewById(R.id.bestEmail);
        final EditText etPassword = findViewById(R.id.newPassword);
        final RadioButton rbHomeless = findViewById(R.id.rd_btn_homelessUser);
        final RadioButton rbShelterOwner = findViewById(R.id.rd_btn_shelterOwner);


        register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("RegisterActivity", "createUserWithEmail:success");
                                        User user;
                                        if (rbHomeless.isChecked()) {
                                            user = new HomelessUser(currUser);
                                            user.writeNewUser();
                                        } else if (rbShelterOwner.isChecked()){
                                            user = new ShelterOwner(currUser);
                                            user.writeNewUser();
                                        }
                                        HelperUI.goToWelcome(context);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("RegisterActivity", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(context, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        HelperUI.goToRegister(context);
                                    }
                                }
                            });
                }
            });

    }
}
