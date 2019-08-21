package com.ma.modernmotivealarm;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class setalarm extends AppCompatActivity implements  Dialogfor_time_pickiing.take_the_time ,dialog_name.exampledialoglistner  {
     Button changevideo;

     ImageButton bt,bt2;
    String name="MOTIVE ALARM",mode="offline",pathon="uhuhiuh",pathoff;
    TextView name_field,show_time;
    private int GALLERY = 1;
    Uri contentURI;
    int hours,minutes;
    VideoView vv;
    Switch sw;
    int id,flag;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);
      name_field = findViewById(R.id.textView11);
      changevideo=findViewById(R.id.changevideo);
        AdView adView = findViewById(R.id.adView11);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        vv=findViewById(R.id.v564ideoView1313);
        bt=findViewById(R.id.ageBu);
        bt2=findViewById(R.id.ageBu1);
        sw= findViewById(R.id.switch2);
        show_time = findViewById(R.id.textView5);
        imageView=findViewById(R.id.imageView9);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog_for_time();
            }
        });
        load_if_edit();
        yesdoit();


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked)
              {  Intent intr =new Intent(buttonView.getContext(),setalarmonline.class);
                   intr.putExtra("name",name_field.getText().toString());
                  intr.putExtra("hour",hours);
                  intr.putExtra("minute",minutes);
                    startActivity(intr);
                  Toast.makeText(buttonView.getContext(),"YOU SIWTCHED TO ONLINE MODE",Toast.LENGTH_LONG).show();

              }
              else
              {   sw.setChecked(false);
                  Toast.makeText(buttonView.getContext(),"YOU SIWTCHED TO OFFLINE MODE",Toast.LENGTH_LONG).show();
              }
          }
      });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putdataandgo();
            }
        });

        ConstraintLayout cn =findViewById(R.id.touchmename);
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });


        show_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog_for_time();
            }
        });


         changevideo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 chooseVideoFromGallary();
             }
         });


bt2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences sha2 =getSharedPreferences("edit_alarm",MODE_PRIVATE);
        SharedPreferences.Editor gh= sha2.edit();
        gh.putInt("edit_alarm",0);
        gh.commit();
        startActivity(new Intent(setalarm.this, MainActivity.class));

    }
});
               vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                   @Override
                   public void onCompletion(MediaPlayer mp) {
                       mp.start();
                   }
               });
    }




     public void putdataandgo()
     {
         Intent in =new Intent(this,MainActivity.class);
         in.putExtra("mode_add",0);
         in.putExtra("hour_add",hours);
         in.putExtra("minute_add",minutes);
         in.putExtra("name_add",name_field.getText().toString());
         in.putExtra("path_online_add",pathon);

         in.putExtra("path_offline_add",contentURI.toString());


         in.putExtra("id",id);
         SharedPreferences adt1 =getSharedPreferences("additional data",MODE_PRIVATE);
         SharedPreferences.Editor  editor11 = adt1.edit();
         editor11.putInt("additional_data",1);
         editor11.apply();
         startActivity(in);



     }
     public void yesdoit()
     {
       int flags= getIntent().getIntExtra("yes",0);
       if(flags==1)
       {     name_field.setText(getIntent().getStringExtra("name"));
           hours = getIntent().getIntExtra("hour", 0);
           minutes = getIntent().getIntExtra("minute", 0);
           id = getIntent().getIntExtra("id", 0);
           taketime(hours,minutes);
       }
     }
    public void load_if_edit ()
    {          SharedPreferences sha =getSharedPreferences("edit_alarm",MODE_PRIVATE);
                  flag = sha.getInt("edit_alarm",0);
                 if(flag==1) {
                     name_field.setText(getIntent().getStringExtra("name"));
                     hours = getIntent().getIntExtra("hour", 0);
                     minutes = getIntent().getIntExtra("minutes", 0);
                     id = getIntent().getIntExtra("id", 0);
                     taketime(hours,minutes);
                     contentURI= Uri.parse(getIntent().getStringExtra("path"));
                     vv.setVideoURI(contentURI);
                     vv.requestFocus();
                     vv.start();


                 }

                 else
                 {
                     String defaulta ="android.resource://" + getPackageName() + "/" + R.raw.sampleplay;//todo change deffault local video
                     contentURI= Uri.parse(defaulta);
                     vv.setVideoURI(contentURI);
                     vv.requestFocus();
                     vv.start();
                 }


    }
    public void opendialog()
    {

        dialog_name di= new dialog_name();
        di.show(getSupportFragmentManager(),"ma dialog");

    }
    public void opendialog_for_time()
    {

        Dialogfor_time_pickiing di= new Dialogfor_time_pickiing();
        di.show(getSupportFragmentManager(),"ma dialog");

    }
    @Override
    public void applyteaxt(String namee) {
        name_field.setText(namee);
        name=namee;
    }
    public void taketime(int hour, int minute) {
           hours=hour;
           minutes=minute;
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
    }
    public void chooseVideoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("result",""+resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            Log.d("what","cancle");
            return;
        }
        if (requestCode == GALLERY) {
            Log.d("what","gale");
            if (data != null) {

                contentURI = data.getData();
                vv.setVideoURI(contentURI);
                vv.requestFocus();
                vv.start();
                pathoff = contentURI.toString();





            }

        }
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this,"Tap on cross to cancel alarm activation",Toast.LENGTH_LONG).show();
    }
}
