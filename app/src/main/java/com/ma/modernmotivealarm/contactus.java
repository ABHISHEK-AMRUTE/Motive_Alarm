package com.ma.modernmotivealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class contactus extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        TextView img= findViewById(R.id.textView22);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "abhishekamrutelfs@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Motive Alarm Feedback");

                startActivity(intent);
            }
        });


        Button bt= findViewById(R.id.button3);
        MobileAds.initialize(this,
                "ca-app-pub-2605690072930758~2468887768");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                startActivity(new Intent(contactus.this,MainActivity.class));// Code to be executed when the interstitial ad is closed.
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handelitman();
            }
        });
    }

    private void handelitman() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        else
        {
            startActivity(new Intent(contactus.this,MainActivity.class));// Code to be executed when the interstitial ad is closed.
        }
    }

    @Override
    public void onBackPressed() {
        handelitman();
    }
}
