package com.ma.modernmotivealarm;

import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;

import android.view.KeyEvent;
import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.InterstitialAd;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class wakemeup_youtube extends YouTubeBaseActivity implements   YouTubePlayer.OnInitializedListener  {
    String onlinelink ="onlinelink";
    String timedelay="time_delay";
    String numberofprobl ="numberofprob";
    int count_of_problems=1;
    String path;
    EditText get;
    TextView txt1;
    String level_of_math="level_of_math";
    int flag=0;
    int time_delay;
    CountDownTimer cou;
    String api_key="AIzaSyCsQzA3g_9EvVOuTfnCP9v0NH6oXYXVLmA";
    YouTubePlayerView vv;
    Button bt;
    TextView timer;
    MediaPlayer mp4,mp5;
    int level;
    SharedPreferences mlevel ;
    AudioManager audioManager;

    int a,b,c,d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakemeup_youtube);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        SharedPreferences dfg= getSharedPreferences("switch_id",MODE_PRIVATE);
        SharedPreferences.Editor e =dfg.edit();
        e.putInt("switch_id",getIntent().getIntExtra("switch_id",-1));
        e.commit();

        SharedPreferences mlevel =getSharedPreferences(level_of_math,MODE_PRIVATE);
        level=mlevel.getInt("level",1);

          path= getIntent().getStringExtra("video_path");
          if(path==null)
              path="moGTAWG8yHo";
        SharedPreferences prob =getSharedPreferences(numberofprobl,MODE_PRIVATE);
        count_of_problems=prob.getInt("numb_prob",2);
        count_of_problems--;
        SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
        time_delay=timed.getInt("time_delay",15);
        bt=findViewById(R.id.button4);
        vv=findViewById(R.id.youTubePlayerView);
      get= findViewById(R.id.editText3);
     txt1=findViewById(R.id.textView4);

     timer=findViewById(R.id.timer1);
        mp4=  MediaPlayer.create(getApplicationContext(),R.raw.mid);
        mp5=  MediaPlayer.create(getApplicationContext(),R.raw.al2);




        cou = new CountDownTimer(time_delay*1000,1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

            }
        }.start();


       audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        for(int x=0;x<=15;x++)
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
       for(int x=0;x<=2;x++)
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
        mp5.start();


        mp4.start();
        mp5.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mp4.stop();

                vv.initialize(api_key,wakemeup_youtube.this);
            }
        }, time_delay*1000);






    settext();




    }

    public void settext()
    {
        a=new Random().nextInt(100) ;

        b=new Random().nextInt(100) ;
        c=new Random().nextInt(100) ;
        if(level==1)
        {
            txt1.setText(a+ " + "+ b+" + "+c+"");
        }
        else
        {
            txt1.setText(a+ " + ("+ b+" * "+c+")");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return true;
    }


    public void stopservice(View v)
    {    if(get.getText().toString().equals(""))
    {
        Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        return;
    }

        d = new Integer(get.getText().toString());
        if(d==(a+b+c)&&level==1)
        {     flag=1;
            Toast.makeText(wakemeup_youtube.this,"Well done GOOD MORNING",Toast.LENGTH_SHORT).show();

            mp4.stop();
            mp5.stop();
            if(count_of_problems==0)
            {


                finish();
                Intent in=new Intent(this,MainActivity.class);
                startActivity(in);


            }
            else
            {
                count_of_problems--;
                Toast.makeText(this, "Problem number: "+count_of_problems+1, Toast.LENGTH_SHORT).show();
                settext();
            }

        }
        else if(d==(a+b*c)&&level==2)
        {    flag=1;
            Toast.makeText(wakemeup_youtube.this,"Well done GOOD MORNING",Toast.LENGTH_SHORT).show();

            mp4.stop();
            mp5.stop();
            if(count_of_problems==0)
            {
                finish();
                Intent in=new Intent(this,MainActivity.class);
                startActivity(in);
            }
            else
            {
                count_of_problems--;
                Toast.makeText(this, "Problem number: "+count_of_problems+1, Toast.LENGTH_SHORT).show();
                settext();
            }
        }
        else
        {
            Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

    Timer timerw;
    wakemeup_youtube.MyTimerTask myTimerTask;

    @Override
    protected void onResume() {
        super.onResume();
        mp5.start();
        mp4.start();


        if (timerw != null&&flag==0) {
            timerw.cancel();
            timerw = null;
        }
    }

    @Override
    protected void onPause() {
        if (timerw == null&&flag==0) {
            myTimerTask = new MyTimerTask();
            timerw = new Timer();
            timerw.schedule(myTimerTask, 100, 100);
        }

        super.onPause();
    }

    private void bringApplicationToFront() {
        KeyguardManager myKeyManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (myKeyManager.inKeyguardRestrictedInputMode())
            return;



        Intent notificationIntent = new Intent(this, wakemeup_youtube.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {

    }


  @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,final YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(path);
        youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {
                youTubePlayer.play();
            }

            @Override
            public void onStopped() {
                youTubePlayer.seekToMillis(1000);
            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            bringApplicationToFront();
        }

    }



}

