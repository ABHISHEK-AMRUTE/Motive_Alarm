package com.ma.modernmotivealarm;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class setalarmonline extends YouTubeBaseActivity   {
    Button setname,settime,changevideo;
    ImageButton bt,bt2;
    String name="MOTIVE ALARM",mode="offline",pathon="moGTAWG8yHo",pathoff;
    TextView name_field,show_time;
    YouTubePlayerView vv;
    String path="moGTAWG8yHo";
    config bh;
    int hour=0;
    int minute=0;
    FragmentManager fg;
    Switch sw;
    int id,flag;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarmonline);
        name_field = findViewById(R.id.textView112);
        changevideo=findViewById(R.id.changevideo2);
        settime=findViewById(R.id.timeedit2);
        setname =findViewById(R.id.nameedit2);
        show_time=findViewById(R.id.textView52);
        vv=findViewById(R.id.v564ideoView13132);
        bt=findViewById(R.id.ageBu2);
        bt2=findViewById(R.id.ageBu12);
        sw= findViewById(R.id.switch22);
        sw.setChecked(true);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sha2 =getSharedPreferences("edit_alarm",MODE_PRIVATE);
                SharedPreferences.Editor gh= sha2.edit();
                gh.putInt("edit_alarm",0);
                gh.commit();
                startActivity(new Intent(setalarmonline.this, MainActivity.class));
            }
        });

        path= getIntent().getStringExtra("onlinepath");
        if(path==null)
            path="moGTAWG8yHo";
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {   Toast.makeText(buttonView.getContext(),"YOU SIWTCHED TO ONLINE MODE",Toast.LENGTH_LONG).show();


                }
                else
                {   sw.setChecked(false);
                sw.setBackground(getDrawable(R.color.online));
                    Toast.makeText(buttonView.getContext(),"YOU  SIWTCHED TO OFFLINE MODE",Toast.LENGTH_LONG).show();
                    Intent intr =new Intent(buttonView.getContext(),setalarm.class);
                    intr.putExtra("name",name_field.getText().toString());
                    intr.putExtra("hour",hour);
                    intr.putExtra("minute",minute);
                    intr.putExtra("yes",1);
                    // 1 - yes do it.
                    startActivity(intr);
                }
            }
        });
        changevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getit =   new Intent(setalarmonline.this,getlink.class);
                getit.putExtra("name",name_field.getText().toString());
                getit.putExtra("hour",hour);
                getit.putExtra("minute",minute);
                getit.putExtra("id",id);
                getit.putExtra("path",path);
                startActivity(getit);
            }
        });


            load_if_edit();


        onInitializedListener = new YouTubePlayer.OnInitializedListener()
        {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                enableDisableView(vv, true);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                youTubePlayer.loadVideo(path);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult
                    youTubeInitializationResult) {
                Toast.makeText(setalarmonline.this,"Please update your youtube app",Toast.LENGTH_LONG).show();

            }
        };
        vv.initialize("AIzaSyBjJe0WTX-xUwHFbGk77oguYDpxr96JTuQ",onInitializedListener);


        name_field.setText(getIntent().getStringExtra("name"));


        hour= getIntent().getIntExtra("hour",0);
        minute= getIntent().getIntExtra("minute",0);



        if(hour<10&&minute<10)
        {
            show_time.setText("0"+hour+":0"+minute);
        }else     if(hour<10&&minute>10)
        {
            show_time.setText("0"+hour+":"+minute);
        }else
        if(hour>10&&minute<10)
        {
            show_time.setText(hour+":0"+minute);
        }else

            show_time.setText(hour+":"+minute);




            name_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                          gettimee();
            }
        });
        show_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettimee();
            }
        });
        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettimee();
            }
        });setname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettimee();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(setalarmonline.this,MainActivity.class);
                in.putExtra("mode_add",1);
                in.putExtra("hour_add",hour);
                in.putExtra("minute_add",minute);
                in.putExtra("name_add",name_field.getText().toString());
                in.putExtra("path_online_add",path);
                //in.putExtra("path_offline_add",contentURI.toString());
                SharedPreferences adt1 =getSharedPreferences("additional data",MODE_PRIVATE);
                SharedPreferences.Editor  editor11 = adt1.edit();
                editor11.putInt("additional_data",1);
                editor11.apply();
                startActivity(in);

            }
        });
    }

    @Override
    public void onBackPressed() {
Toast.makeText(this,"Tap on cross to cancel alarm activation",Toast.LENGTH_LONG).show();
    }

    public void load_if_edit ()
    {          SharedPreferences sha =getSharedPreferences("edit_alarm",MODE_PRIVATE);
        flag = sha.getInt("edit_alarm",0);
        if(flag==1) {
            name_field.setText(getIntent().getStringExtra("name"));
            name=getIntent().getStringExtra("name") ;
            hour = getIntent().getIntExtra("hour", 0);
            minute = getIntent().getIntExtra("minutes", 0);
            id = getIntent().getIntExtra("id", 0);
             path= getIntent().getStringExtra("path");


        }


    }
 void gettimee()
 {
     Intent intent =new Intent(this,gettime.class);
     intent.putExtra("name",name_field.getText().toString());
     intent.putExtra("onlinepath",path);
       startActivity(intent);
 }
}
