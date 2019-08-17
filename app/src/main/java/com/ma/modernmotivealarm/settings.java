package com.ma.modernmotivealarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class settings extends AppCompatActivity {
RadioButton hard,easy;
EditText editText,editText1;
String numberofprobl ="numberofprob";
Button set,submit,setprob;
String level_of_math="level_of_math";
    String timedelay="time_delay";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        hard=findViewById(R.id.radioButton);easy=findViewById(R.id.radioButton2);
       editText=findViewById(R.id.editText4);
        setprob=findViewById(R.id.setproblem);
        editText1=findViewById(R.id.editText51);
       set=findViewById(R.id.set);
       submit=findViewById(R.id.sbubmit);
        SharedPreferences a1 =getSharedPreferences(numberofprobl,MODE_PRIVATE);
        editText1.setText(""+a1.getInt("numb_prob",2));
        SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
        int ti=timed.getInt("time_delay",15);
        editText.setText(ti+"");
        SharedPreferences sharedPreferences2 =getSharedPreferences(level_of_math,MODE_PRIVATE);
        if(sharedPreferences2.getInt("level",1)==1)
        {
            hard.setChecked(false);
            easy.setChecked(true);
        }
        else
        {
            easy.setChecked(false);
            hard.setChecked(true);
        }
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(easy.isChecked())
                {
                    easy.setChecked(false);
                }
                hard.setChecked(true);
                SharedPreferences levelofmath =getSharedPreferences(level_of_math,MODE_PRIVATE);
                final SharedPreferences.Editor editor = levelofmath.edit();
                editor.putInt("level",2);
                editor.apply();

            }
        });
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hard.isChecked())
                {
                    hard.setChecked(false);
                }
                easy.setChecked(true);
                SharedPreferences levelofmath =getSharedPreferences(level_of_math,MODE_PRIVATE);
                final SharedPreferences.Editor editor = levelofmath.edit();
                editor.putInt("level",1);
                editor.apply();
            }
        });
        setprob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int problems=Integer.parseInt(editText1.getText().toString());
                SharedPreferences prob =getSharedPreferences(numberofprobl,MODE_PRIVATE);
                final SharedPreferences.Editor editor23 = prob.edit();
                editor23.putInt("numb_prob",problems);
                editor23.apply();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                Toast.makeText(settings.this,"Done!",Toast.LENGTH_LONG).show();


            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                int time=Integer.parseInt(editText.getText().toString());
                SharedPreferences timed =getSharedPreferences(timedelay,MODE_PRIVATE);
                final SharedPreferences.Editor editor = timed.edit();
                editor.putInt("time_delay",time);
                editor.apply();
                Toast.makeText(settings.this,"wakeup time:"+time+" seconds",Toast.LENGTH_LONG).show();

            }
        });
       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Intent in = new Intent(settings.this,MainActivity.class);
                     startActivity(in);

            }
        });

    }
}
