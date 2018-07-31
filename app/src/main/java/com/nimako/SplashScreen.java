package com.nimako;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        Thread th=new Thread(){

            @Override
            public void run() {
                try
                {
                    Thread.sleep(7000);

//                    Thread.sleep(1000);
                    finish();
                    Intent i=new Intent(SplashScreen.this,Login.class);
                    startActivity(i);

                }catch(Exception e)
                {

                    e.printStackTrace();
                }
            }
        };
        th.start();
    }
}
