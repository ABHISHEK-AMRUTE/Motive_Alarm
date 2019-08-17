package com.ma.modernmotivealarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class starter extends AppCompatActivity {
  //  VideoView vv;
    private static int SPLASH_TIME_OUT =2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_starter);
        SharedPreferences sharedPreferences =getSharedPreferences("first_time",MODE_PRIVATE);
       int i= sharedPreferences.getInt("first_time",0);//0 for first time
        if(i==0) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent in = new Intent(starter.this, onBoardingActivity.class);
                    startActivity(in);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
        else
        { new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(starter.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        }, 0);

        }




    }
}
