package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.utils.HelperUI;

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
                    HelperUI.signOut(context);
                }
        });

        Button toMap = findViewById(R.id.btn_to_map);
        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperUI.goToMap(context);
            }
        });
    }

    @Override
    public void onBackPressed() {
        HelperUI.goToWelcome(context);
    }


}
