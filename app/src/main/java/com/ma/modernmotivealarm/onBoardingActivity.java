package com.ma.modernmotivealarm;

import android.content.Intent;
import android.os.Bundle;


import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class onBoardingActivity extends AppCompatActivity {

   ViewPager vp;
   LinearLayout ll;
   Button back,next;
   private TextView[] mdots;
   int mcurrentpage;
   sliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boarding);
        vp=findViewById(R.id.viewpager);
        ll=findViewById(R.id.null1);
        back=findViewById(R.id.back);
        next =findViewById(R.id.next);
        sliderAdapter =new sliderAdapter(this);
        vp.setAdapter(sliderAdapter);
        adddots(0);
        vp.addOnPageChangeListener(viewlistner);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mcurrentpage==3)
                {
                    startActivity(new Intent(onBoardingActivity.this,how_to_video.class));
                }
                else
                {
                    vp.setCurrentItem(mcurrentpage+1);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(mcurrentpage-1);
            }
        });
    }


    public  void adddots(int position)
    {
        mdots= new TextView[4];
        ll.removeAllViews();
        for(int i=0;i<mdots.length;i++)
        {
            mdots[i] =new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.dots));
            ll.addView(mdots[i]);
        }
        if(mdots.length>0)
        {
            mdots[position].setTextColor(getResources().getColor(R.color.dotswh));
        }
    }
    ViewPager.OnPageChangeListener viewlistner =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
          adddots(i);
          mcurrentpage=i;
          if(i==0)
          {
            next.setEnabled(true);
            back.setEnabled(false);
            back.setVisibility(View.INVISIBLE);
            next.setText("NEXT");
            back.setText("");
          }
          else if(i==mdots.length-1)
          {
              next.setEnabled(true);
              back.setEnabled(true);
              back.setVisibility(View.VISIBLE);
              next.setText("Finish");
              back.setText("BACK");
          }
          else
          {
              next.setEnabled(true);
              back.setEnabled(true);
              back.setVisibility(View.VISIBLE);
              next.setText("NEXT");
              back.setText("BACK");
          }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
