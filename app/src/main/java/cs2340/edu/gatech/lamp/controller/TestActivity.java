package cs2340.edu.gatech.lamp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Shelter;

public class TestActivity extends AppCompatActivity {

    TextView name;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        name = findViewById(R.id.textView2);
        info = findViewById(R.id.textView3);
        name.setText(Shelter.getShelter(bundle.getInt("position")).getName());
        info.setText(Shelter.getShelter(bundle.getInt("position")).getDetails());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
