package com.ma.modernmotivealarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class showmyalarmoffline extends AppCompatActivity {
    TextView time ,name;
    ImageButton goback;
    VideoView vv;
    AdView mAdView;
    ImageView imageView;
     Uri setvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmyalarmoffline);

         vv=findViewById(R.id.v564ideoView1313);
        time=findViewById(R.id.textView5);
        goback =findViewById(R.id.imageButton);
        name=findViewById(R.id.textView11);
        imageView= findViewById(R.id.imageView2);



        MobileAds.initialize(this,
                "ca-app-pub-2605690072930758~2468887768");

        mAdView = findViewById(R.id.adview3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sha =getSharedPreferences("edit_alarm",MODE_PRIVATE);
                SharedPreferences.Editor  editw = sha.edit();
                editw.putInt("edit_alarm",1); // 1 edit and 0 do not edit
                editw.commit();
                Intent igo =new Intent(showmyalarmoffline.this,setalarm.class);

                igo.putExtra("name",name.getText().toString());
                igo.putExtra("hour",getIntent().getIntExtra("hour",00));
                igo.putExtra("minutes",getIntent().getIntExtra("minute",00));
                igo.putExtra("id",getIntent().getStringExtra("position"));
                igo.putExtra("path",getIntent().getStringExtra("videopath"));
                startActivity(igo);

            }
        });
        setvideo= Uri.parse(getIntent().getStringExtra("videopath"));
        vv.setVideoURI(setvideo);


        vv.requestFocus();
        vv.start();
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                String defaulta ="android.resource://" + getPackageName() + "/" + R.raw.sampleplay;//todo change deffault local video
                Uri contentURI= Uri.parse(defaulta);
                vv.setVideoURI(contentURI);
                vv.requestFocus();

                vv.start();


                return false;
            }
        });
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        int hour= getIntent().getIntExtra("hour",00);
        int minute= getIntent().getIntExtra("minute",00);
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


    }

    @Override
    protected void onResume() {

        super.onResume();
        if(!vv.isPlaying()){

            vv.start();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
