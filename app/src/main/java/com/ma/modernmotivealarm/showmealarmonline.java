package com.ma.modernmotivealarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class showmealarmonline extends YouTubeBaseActivity {
    YouTubePlayer.OnInitializedListener onInitializedListener;
    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }
    TextView time,name;
    YouTubePlayerView vv;
    config bh;
    ImageButton bt;
    AdView mAdView;
    ImageView imageView;
    String path="moGTAWG8yHo";
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmealarmonline);
           vv=findViewById(R.id.youTubePlayerView);
           time=findViewById(R.id.textView51);
           name =findViewById(R.id.textView111);
           bt=findViewById(R.id.imageButton1);
        int hour= getIntent().getIntExtra("hour",00);
        int minute= getIntent().getIntExtra("minute",00);
        MobileAds.initialize(this,
                "ca-app-pub-2605690072930758~2468887768");

        mAdView = findViewById(R.id.adView11);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageView= findViewById(R.id.imageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sha =getSharedPreferences("edit_alarm",MODE_PRIVATE);
                SharedPreferences.Editor  editw = sha.edit();
                editw.putInt("edit_alarm",1); // 1 edit and 0 do not edit
                editw.commit();
                Intent igo =new Intent(showmealarmonline.this,setalarmonline.class);

                igo.putExtra("name",name.getText().toString());
                igo.putExtra("hour",getIntent().getIntExtra("hour",00));
                igo.putExtra("minute",getIntent().getIntExtra("minute",00));
                igo.putExtra("id",getIntent().getStringExtra("position"));
                igo.putExtra("path",getIntent().getStringExtra("videopath"));
                startActivity(igo);

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(hour<10&&minute<10)
        {
            time.setText("0"+hour+":0"+minute);
        }else     if(hour<10&&minute>10)
        {
            time.setText("0"+hour+":"+minute);
        }else
        if(hour>10&&minute<10)
        {
            time.setText(hour+":0"+minute);
        }else

            time.setText(hour+":"+minute);


        name.setText(getIntent().getStringExtra("name"));
        path= getIntent().getStringExtra("videopath");
        if(path=="")
            path="moGTAWG8yHo";
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                enableDisableView(vv, true);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                if(path=="")
                    path="moGTAWG8yHo";
                youTubePlayer.loadVideo(path);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult
                    youTubeInitializationResult) {

            }
        };
        vv.initialize("AIzaSyCsQzA3g_9EvVOuTfnCP9v0NH6oXYXVLmA",onInitializedListener);
    }

}

