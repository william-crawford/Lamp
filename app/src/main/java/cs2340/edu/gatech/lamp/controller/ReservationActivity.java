package cs2340.edu.gatech.lamp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.HomelessUser;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Shelter;
//import cs2340.edu.gatech.lamp.model.ShelterUser;
//import cs2340.edu.gatech.lamp.utils.ShelterManager;

public class ReservationActivity extends AppCompatActivity {

    private ImageView lever;
    private float rotation;
    private Shelter shelter;

    private TextView cap;
    private TextView available;
    private TextView user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        String shelterID = getIntent().getStringExtra("shelterID");
        System.out.println("ShelterID is: \n\n");
        System.out.println(shelterID);
        shelter = Model.getInstance().getShelterByKey(shelterID);

        cap = findViewById(R.id.txt_res_cap);
        available = findViewById(R.id.txt_res_available);
        user = findViewById(R.id.txt_res_user);

        lever = findViewById(R.id.lever);
        rotateLever(180);
        updateText();
    }

    private void rotateLever(float angle) {
        RotateAnimation rotateAnim = new RotateAnimation(rotation, angle,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.8f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        lever.startAnimation(rotateAnim);
        if (rotation > 0 && angle <= 0 && rotation < 90) {
            decrease();
        } else if (angle > 0 && rotation <= 0 && angle < 90) {
            increase();
        }

        rotation = angle;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                int[] pos = new int[2];
                lever.getLocationOnScreen(pos);
                float pivotX = pos[0] + lever.getWidth() / 2;
                float pivotY = pos[1] + 4 * lever.getHeight() / 5;
                float angle = (float) (Math.atan2(x - pivotX, pivotY - y) * 180 / Math.PI);
                rotateLever(angle);

        }
        return super.onTouchEvent(event);
    }

    private void increase() {
        if (!shelter.increaseReservation((HomelessUser) Model.getInstance().getCurrentUser())) {
            Toast.makeText(this, "Shelter is full", Toast.LENGTH_SHORT).show();
        }
        Model.save(this);
        updateText();
    }

    private void decrease() {
        if(!shelter.decreaseReservation((HomelessUser) Model.getInstance().getCurrentUser())) {
            Toast.makeText(this, "Turn other way to make reservation", Toast.LENGTH_SHORT).show();
        }
        Model.save(this);
        updateText();
    }

    private void updateText() {
        cap.setText("Shelter Capacity: " + shelter.getCapacity());
        available.setText(String.format(Locale.US, "Spaces Vacant: %d", shelter.getSpacesVacant()));

        user.setText(String.format(Locale.US, "You have reserved %d spaces",
                shelter.getSpacesFilled()));

        //        shelter.getReservation(Model.getInstance().getCurrentUser().getUserID()) != null ?
        //        shelter.getReservation(Model.getInstance().getCurrentUser().getUserID()).getSpacesReserved() : 0
        //));
    }

}
