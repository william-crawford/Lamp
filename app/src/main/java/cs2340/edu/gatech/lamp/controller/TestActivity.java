package cs2340.edu.gatech.lamp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import cs2340.edu.gatech.lamp.R;

public class TestActivity extends AppCompatActivity {

    TextView name;
    TextView addr;
    TextView phone;
    int sID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        name = findViewById(R.id.textView2);
        addr = findViewById(R.id.textView3);
        phone = findViewById(R.id.textView4);
        if(bundle != null) {
            String title = bundle.getString("sName");
            title += bundle.getBoolean("hasSpace") ? Html.fromHtml("<font color=\"#00CC00\">\t \u2714</font>"):
                    Html.fromHtml("<color = \"#CC0000\"><b>\t\t\u2718</b></font>");
            name.setText(title);
            addr.setText(Html.fromHtml("<b>Address:</b> " + bundle.getString("sAddress")));
            phone.setText(Html.fromHtml("<b>Phone:</b> " + bundle.getString("number")));
            sID = bundle.getInt("id");
        } else {
            name.setText(Html.fromHtml("<b>Oops! This is awkward.</b>"));
            addr.setText("");
            phone.setText("");
        }
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
