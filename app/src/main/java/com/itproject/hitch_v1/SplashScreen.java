package com.itproject.hitch_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=20) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
    }
}
