package com.ma.modernmotivealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class how_to_video extends AppCompatActivity {
ImageButton bt;
TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_video);
        SharedPreferences sharedPreferences =getSharedPreferences("first_time",MODE_PRIVATE);
        SharedPreferences.Editor edt= sharedPreferences.edit();
        edt.putInt("first_time",1);
        edt.commit();
        bt=findViewById(R.id.imageButton3);
        txt=findViewById(R.id.textView18);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(how_to_video.this,"Initialising... ",Toast.LENGTH_LONG).show();
                startActivity(new Intent(how_to_video.this,MainActivity.class));

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=df-6eROdL_4")));
            }
        });
    }
}
