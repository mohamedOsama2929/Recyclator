package com.example.recyclator.recyclator;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent intent = new Intent(this, CombanyOrUser.class);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }).start();
    }


    }

