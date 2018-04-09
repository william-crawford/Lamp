package cs2340.edu.gatech.lamp.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import cs2340.edu.gatech.lamp.controller.*;

/**
 * Created by Tucker on 2/26/2018.
 */

public class HelperUI {
    public static void signOut(final Context context) {
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        Toast.makeText(context, "Logout successful", Toast.LENGTH_LONG).show();
                        goToWelcome(context);
                    }
                });
    }

    public static void goToDefault(Context context) {
        //context.startActivity(new Intent(context, DefaultActivity.class));
        Intent intent = new Intent(context, ListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(new Intent(context, ListActivity.class));
    }

    public static void goToList(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(new Intent(context, ListActivity.class));
    }

    public static void goToWelcome(Context context) {
        Intent welcomeIntent = new Intent(context, WelcomeActivity.class);
        welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(welcomeIntent);
    }

    public static void goToRegister(Context context) {
        Intent welcomeIntent = new Intent(context, WelcomeActivity.class);
        welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(welcomeIntent);
    }

    public static void goToMap(Context context) {
        context.startActivity(new Intent(context, MapsActivity.class));
    }
}
