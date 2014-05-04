package net.saga.aeroconf.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import net.saga.aeroconf.app.R;

class WelcomeScreen extends Activity {

    public static final String AUTHORIZED = "org.saga.conf.AUTHORIZED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.contains(AUTHORIZED)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, AuthorizeActivity.class));
            finish();
        }

    }
}
