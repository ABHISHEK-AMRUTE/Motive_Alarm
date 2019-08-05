package com.ma.modernmotivealarm;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class sliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public sliderAdapter(Context context)
    {
        this.context =context;
    }
    public String[] slide_headings = {
      "Wake up with your favorite video","Easy and fast alarm setup","Customize in your way","Set alarms with different videos and modes"
    };
    public int[] slide_images =
            {
                   R.drawable.s,
                    R.drawable.sss,
                    R.drawable.ss,
                    R.drawable.ssss,

            };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout) o ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view =layoutInflater.inflate(R.layout.slidelayout,container,false);
        ImageView img= view.findViewById(R.id.imageView5);
        TextView text =view.findViewById(R.id.textView15);
        img.setImageResource(slide_images[position]);
        text.setText(slide_headings[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
