package cs2340.edu.gatech.lamp.controller;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cs2340.edu.gatech.lamp.R;

public class DefaultActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        Button logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Logout successful", Toast.LENGTH_LONG).show();
                    goToWelcome();
                }
        });

        Button toMap = findViewById(R.id.btn_to_map);
        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap();
            }
        });
        Button toList = findViewById(R.id.btn_to_list);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });
    }

    @Override
    public void onBackPressed() {
        goToWelcome();
    }

    private void goToWelcome() {
        Intent welcomeIntent = new Intent(context, WelcomeActivity.class);
        welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(welcomeIntent);
    }

    private void goToMap() {
        startActivity(new Intent(this, MapsActivity.class));
    }

    private void goToList() {
        startActivity(new Intent(this, ListActivity.class));
    }
}
