package com.smartpathsystems.sps;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    //duljina prikazivanja welcome screena u ms
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //handleri se koriste za komunikaciju sa threadovima
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent je namjera za otvorit novu aktivnost, novi dio aplikacije
                Intent homeIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
