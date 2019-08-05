package com.ma.modernmotivealarm;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class gettime extends AppCompatActivity {
Button bt;
TimePicker tm;


EditText name;

int hour,minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettime);
        bt=findViewById(R.id.button6);
        tm=findViewById(R.id.button5);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



       name= findViewById(R.id.editText2);


        name.setText(getIntent().getStringExtra("name"));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,tm.getCurrentHour());
                calendar.set(Calendar.MINUTE,tm.getCurrentMinute());

                hour = tm.getCurrentHour();
                minute = tm.getCurrentMinute();
               Intent in =new Intent(gettime.this,setalarmonline.class);
                      in.putExtra("name",name.getText().toString());
                      in.putExtra("hour",hour);
                      in.putExtra("minute",minute);
                      in.putExtra("onlinepath",getIntent().getStringExtra("onlinepath"));
        startActivity(in);

            }
        });

    }
}
