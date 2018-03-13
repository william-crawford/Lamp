package cs2340.edu.gatech.lamp.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

import cs2340.edu.gatech.lamp.R;

public class TestActivity extends AppCompatActivity {

    TextView name;
    TextView addr;
    TextView phone;
    String sID;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        name = findViewById(R.id.textView2);
        addr = findViewById(R.id.textView3);
        phone = findViewById(R.id.textView4);
        image = findViewById(R.id.picture);
        if(bundle != null) {
            String title = bundle.getString("sName");
            title += bundle.getBoolean("hasSpace") ? Html.fromHtml("<font color=\"#00CC00\">\t \u2714</font>"):
                    Html.fromHtml("<color = \"#CC0000\"><b>\t\t\u2718</b></font>");
            name.setText(title);
            addr.setText(Html.fromHtml("<b>Address:</b> " + bundle.getString("sAddress")));
            phone.setText(Html.fromHtml("<b>Phone:</b> " + bundle.getString("number")));
            sID = bundle.getString("id");
            new UpdateImageFromUrlTask().execute(bundle.getString("url"));
        } else {
            name.setText(Html.fromHtml("<b>Oops! This is awkward.</b>"));
            addr.setText("");
            phone.setText("");
        }
    }

    private class UpdateImageFromUrlTask extends AsyncTask<String, Void, Bitmap> {

        public Bitmap doInBackground(String... input) {
            try {
                URL url = new URL(input[0]);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public void onPostExecute(Bitmap bmp) {
            if (bmp != null) {
                image.setImageBitmap(bmp);
            } else {
                image.setImageDrawable(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark_normal));
            }
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
