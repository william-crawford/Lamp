package cs2340.edu.gatech.lamp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.model.ShelterUser;

public class ReservationActivity extends AppCompatActivity {

    private ImageView lever;
    private float rotation;
    private Shelter shelter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        lever = (ImageView) findViewById(R.id.lever);
        rotateLever(180);
    }

    private void rotateLever(float angle) {
        RotateAnimation rotateAnim = new RotateAnimation(rotation, angle,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.8f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        lever.startAnimation(rotateAnim);
        if (rotation > 0 && angle <= 0 && rotation < 90) {
            shelter.increaseReservation((ShelterUser) Model.getInstance().getCurrentUser());
        } else if (angle > 0 && rotation <= 0 && angle < 90) {
            shelter.decreaseReservation((ShelterUser) Model.getInstance().getCurrentUser());
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

}
