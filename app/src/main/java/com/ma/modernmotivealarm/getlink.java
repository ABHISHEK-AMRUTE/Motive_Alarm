package com.ma.modernmotivealarm;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class getlink extends AppCompatActivity {


Button bt,brow;


        EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getlink);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        bt=findViewById(R.id.button3);
        brow=findViewById(R.id.button);


        ed= findViewById(R.id.editText2);

           brow.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("https://www.youtube.com"));
                   startActivity(browserIntent);
               }
           });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                 String st=ed.getText().toString();
                 int flag=0;
                 for(int x=0;x<st.length();x++)
                 {
                     if(st.charAt(x)=='=')
                     {
                         flag=1;
                     }
                 }
                 if(flag==1)
                 st=st.substring(st.indexOf("=")+1);

                 else
                 {  for(int x=0;x<3;x++)
                     st=st.substring(st.indexOf("/")+1);
                 }



                Intent in=new Intent(getlink.this,setalarmonline.class);

                in.putExtra("name", getIntent().getStringExtra("name"));
                in.putExtra("hour", getIntent().getIntExtra("hour",0));
                in.putExtra("minute", getIntent().getIntExtra("minute",0));
                in.putExtra("id",getIntent().getStringExtra("id"));
                in.putExtra("onlinepath",st);
                startActivity(in);
            }
        });
    }

}
