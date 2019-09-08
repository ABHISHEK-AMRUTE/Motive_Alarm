package com.ma.modernmotivealarm;


import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import com.google.android.material.snackbar.Snackbar;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.VideoView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class wakemeup extends AppCompatActivity {
    int flag=0;
    TextView txt1,timer,note;
    Uri  mImageUri;
    String numberofprobl ="numberofprob";
    int count_of_problems=2;

    CountDownTimer cou;
    String level_of_math="level_of_math";
    EditText  get;
 String path_file="Path_file";
    String timedelay="time_delay";
    int time_delay;
   int a,b,c,d;
    MediaPlayer mp4,mp5;
    VideoView vv;
    AudioManager audioManager;
    String imageUrl;

       int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakemeup);

        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        SharedPreferences mlevel =getSharedPreferences(level_of_math,MODE_PRIVATE);
        level=mlevel.getInt("level",1);
        SharedPreferences prob =getSharedPreferences(numberofprobl,MODE_PRIVATE);
         count_of_problems=prob.getInt("numb_prob",2);
         count_of_problems--;

        SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
        time_delay=timed.getInt("time_delay",15);



        note= findViewById(R.id.textView12);


        SharedPreferences dfg= getSharedPreferences("switch_id",MODE_PRIVATE);
        SharedPreferences.Editor e =dfg.edit();
        e.putInt("switch_id",getIntent().getIntExtra("switch_id",-1));
        e.commit();

        mp4=  MediaPlayer.create(getApplicationContext(),R.raw.mid);
        mp5=  MediaPlayer.create(getApplicationContext(),R.raw.al2);
   timer=findViewById(R.id.timer);
        txt1= findViewById(R.id.textView3);
        get= findViewById(R.id.editText);
        vv = findViewById(R.id.videoView);
        cou = new CountDownTimer(time_delay*1000,1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
timer.setVisibility(View.INVISIBLE);note.setVisibility(View.INVISIBLE);
            }
        }.start();

       imageUrl =  getIntent().getStringExtra("video_path");
        if(imageUrl.matches(""))
        {
            imageUrl="android.resource://" + getPackageName() + "/" + R.raw.sampleplay;
        }
                mImageUri = Uri.parse(imageUrl);


        vv.setVideoURI(mImageUri);
        vv.requestFocus();
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        for(int x=0;x<=15;x++)
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        for(int x=0;x<=2;x++)
       audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);

        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                String defaulta ="android.resource://" + getPackageName() + "/" + R.raw.sampleplay;//todo change deffault local video
                Uri contentURI= Uri.parse(defaulta);
                vv.setVideoURI(contentURI);
                vv.requestFocus();



                return false;
            }
        });
        mp4.start();
        mp5.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mp5.stop();
                mp4.stop();
                vv.start();
                for(int x=0;x<=15;x++)
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);

                SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
                SharedPreferences.Editor edt  = timed.edit();
                edt.putInt("temp_time_delay",time_delay);
                edt.putInt("time_delay",0);
                edt.commit();


            }
        }, time_delay*1000);



          settext();



        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mm) {


                mm.start();
                Toast.makeText(wakemeup.this,time_delay+" main repeat",Toast.LENGTH_LONG);

                 for(int x=0;x<=15;x++) audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
               for(int x=0;x<=1;x++)
                   audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);

            }
        });





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
    protected void onStop() {
        super.onStop();



    }


    @Override
    public void finish() {
        super.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        SharedPreferences sharedPreferences =getSharedPreferences(path_file,MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("path_of_the_file",imageUrl);
        editor.apply();
        editor.commit();
        PowerManager pm=(PowerManager) getSystemService(Context.POWER_SERVICE);


        if(flag==0){
            Intent in=new Intent(this,wakemeup.class);
            startActivity(in);}


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
        {    flag=1;
            Toast.makeText(wakemeup.this,"Well done GOOD MORNING",Toast.LENGTH_SHORT).show();

             mp4.stop();
             mp5.stop();
             if(count_of_problems==0)
             {
                 SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
                 SharedPreferences.Editor edt  = timed.edit();

                 edt.putInt("time_delay",timed.getInt("temp_time_delay",15));
                 edt.commit();




                     finish();
                     Intent in=new Intent(this,MainActivity.class);
                     startActivity(in);




             }
             else
             {
                 count_of_problems--;
                 Toast.makeText(this, "Problem number: "+count_of_problems+1, Toast.LENGTH_SHORT).show();
                 settext();
                 get.setText("");
             }

        }
        else if(d==(a+b*c)&&level==2)
        {    flag=1;
            Toast.makeText(wakemeup.this,"Well done GOOD MORNING",Toast.LENGTH_SHORT).show();

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
                get.setText("");
            }

        }
        else
        {
            Snackbar.make(v, "Wrong Answer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }
    /////////////////////////////////////////////////////////////////////////////////
public void Fullscreencall()
{
    if(Build.VERSION.SDK_INT>11 &&Build.VERSION.SDK_INT<19)
    {
        View v =this.getWindow().getDecorView();
        v.setSystemUiVisibility(View.GONE);
    }
    else if(Build.VERSION.SDK_INT>=19)
    {
        View decorview =getWindow().getDecorView();
        int uioption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorview.setSystemUiVisibility(uioption);
    }
}

    Timer timerw;
    MyTimerTask myTimerTask;

    @Override
    protected void onResume() {
        super.onResume();

           mp5.start();
           mp4.start();
          final SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
          int time = timed.getInt("time_delay",15);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
mp5.stop();mp4.stop();
                Toast.makeText(wakemeup.this,time_delay+"on Resume play",Toast.LENGTH_LONG);

                vv.start();

                SharedPreferences.Editor edt  = timed.edit();
                edt.putInt("temp_time_delay",time_delay);
                edt.putInt("time_delay",0);
                edt.commit();
                for(int x=0;x<=15;x++)
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);

            }
        }, time*1000);

        if (timerw != null&&flag==0) {
            timerw.cancel();
            timerw = null;
        }
    }

    @Override
    protected void onPause() {
        mp5.start();
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



        Intent notificationIntent = new Intent(this, wakemeup.class);
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

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            bringApplicationToFront();
        }

    }



}
