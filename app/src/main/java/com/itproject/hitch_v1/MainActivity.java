package com.itproject.hitch_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button loginDriver;
    private Button loginPassenger;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDriver = (Button)findViewById(R.id.btn_login_driver);
            loginDriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,LoginDriver.class ));
                }
            });

        loginPassenger = (Button) findViewById(R.id.btn_login_passenger);
            loginPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginPassenger.class ));
            }
        });
    }
}
