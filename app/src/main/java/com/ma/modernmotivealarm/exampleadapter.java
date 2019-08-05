package com.ma.modernmotivealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


public class exampleadapter extends RecyclerView.Adapter<exampleadapter.Exampleviewholder> {

    private ArrayList<example_item> mexamplelist;
    private OnitemClickListner mlistner;
    int money=-1;
    PendingIntent pendingIntent1;

    public interface OnitemClickListner
    {
        void onItemClick(int position);
        void ondeleteclick(int position);


    }



    public void setonitemclickedlistner(OnitemClickListner listner)
    {
        mlistner=listner;
    }
    public static  class Exampleviewholder extends RecyclerView.ViewHolder
    {

        public TextView time,text1,text2,id;
        public Switch sw;
        TextView mode_text;

        ImageView vv,delete;

        public Exampleviewholder(@NonNull View itemView, final OnitemClickListner listner) {
            super(itemView);
            time= itemView.findViewById(R.id.textView2);
            id= itemView.findViewById(R.id.take_id);
            text1= itemView.findViewById(R.id.textView3);
             vv=itemView.findViewById(R.id.imageView5);
            delete=itemView.findViewById(R.id.imageView4);
              mode_text= itemView.findViewById(R.id.mode_text);
            sw=itemView.findViewById(R.id.switch1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listner.onItemClick(position);
                        }
                    }
                }
            });
           delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   sw.setChecked(false);
                   if(listner!=null)
                   {
                       int position = getAdapterPosition();
                       if(position!=RecyclerView.NO_POSITION)
                       {
                           listner.ondeleteclick(position);
                       }
                   }
               }
           });



        }
    }
    public exampleadapter(ArrayList<example_item> exaplelist){

        mexamplelist=exaplelist;
    }

    @NonNull
    @Override
    public Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        Exampleviewholder evh= new Exampleviewholder(v,mlistner);
        SharedPreferences dfg= parent.getContext().getSharedPreferences("switch_id",MODE_PRIVATE);
        money = dfg.getInt("switch_id",-1);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Exampleviewholder holder, int i) {

        final example_item currentitem =mexamplelist.get(i);
        //////////////setting switch ////////////////
        if(currentitem.getState()==0)
        {
            holder.sw.setChecked(false);
            holder.sw.setText("OFF");
        }
        else {
            holder.sw.setChecked(true);
            holder.sw.setText("ON");
        }
         if(money==currentitem.getId())
         {  holder.sw.setText("OFF");
             holder.sw.setChecked(false);
         }

        //////setting mode color and text////////////
       if(currentitem.gettext2()==0)
       {
          holder.vv.setImageResource(R.drawable.of);
           holder.mode_text.setText("OFFLINE");
       }
       else
       {
           holder.vv.setImageResource(R.drawable.onn);
           holder.mode_text.setText("ONLINE");
       }


       /////////setting time/////////////
        if(currentitem.gethour()<10&&currentitem.getminute()<10)
        {
            holder.time.setText("0"+currentitem.gethour()+":0"+currentitem.getminute());
        }else     if(currentitem.gethour()<10&&currentitem.getminute()>10)
        {
            holder.time.setText("0"+currentitem.gethour()+":"+currentitem.getminute());
        }else
        if(currentitem.gethour()>10&&currentitem.getminute()<10)
        {
            holder.time.setText(currentitem.gethour()+":0"+currentitem.getminute());
        }else


        holder.time.setText(currentitem.gethour()+":"+currentitem.getminute());

        holder.text1.setText(currentitem.gettext1());

        holder.id.setText(""+(currentitem.getId()));

        holder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                AlarmManager alarmManager  = (AlarmManager) buttonView.getContext().getSystemService(buttonView.getContext().ALARM_SERVICE);

                   /* AlarmManager alarmManager  = (AlarmManager) buttonView.getContext().getSystemService(buttonView.getContext().ALARM_SERVICE);

                   1. take time from the time text
                   2. calculate milli from current time and set alarm
                   3. change colour of the bar
                   4. i.e. change shared preference using id
                  */
                    if(isChecked)
                    {   holder.sw.setText("ON");
                        currentitem.setState(1);
                        int hour = currentitem.gethour();
                        int minute =currentitem.getminute();
                        Calendar ca =Calendar.getInstance();

                        int current_hour = ca.get(Calendar.HOUR_OF_DAY);

                        int current_minute= ca.get(Calendar.MINUTE);
                        int millisec=0;
                        if(hour>current_hour)
                        {
                            millisec= (hour- current_hour)*60*60*1000;
                        }
                        else
                             if(hour<current_hour)
                             {
                                 millisec = ((24-current_hour)+(hour))*60*60*1000;
                             }


                       if(minute>current_minute)
                       {
                           millisec= millisec + ((minute-current_minute)*60*1000);

                       }
                       else if(minute<current_minute)
                       {
                           millisec = millisec +((60-current_minute)+(minute))*60*1000;
                           millisec =millisec  - (60*60*1000);
                       }



                         currentitem.setid(millisec);
                        holder.id.setText(""+(currentitem.getId()));
                        Toast.makeText(buttonView.getContext(),"Alarm will go off in"+ millisec/(60*60*1000)+" hours "+ (millisec/(60*1000))%60+" minutes ",Toast.LENGTH_LONG).show();
                        Intent intent ;
                          if(currentitem.mode==0)
                          {
                           //offline
                               intent = new Intent(buttonView.getContext(), reciver1.class);
                           intent.putExtra("video_path",currentitem.getPath_offline());
                           intent.putExtra("switch_id",currentitem.getId());
                              intent.putExtra("name",currentitem.gettext1());

                          }
                          else
                          {    intent = new Intent(buttonView.getContext(), reciver2.class);
                               intent.putExtra("video_path",currentitem.getPath_online());
                               intent.putExtra("switch_id",currentitem.getId());
                              intent.putExtra("name",currentitem.gettext1());
                              //onilne
                          }

                       pendingIntent1 = PendingIntent.getActivity(buttonView.getContext(), millisec, intent, 0);
                        pendingIntent1 = PendingIntent.getBroadcast(buttonView.getContext(), millisec, intent, 0);

                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ millisec,pendingIntent1);
                        else
                            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime()+ millisec,pendingIntent1);


                    }
                    else
                    {   Toast.makeText(buttonView.getContext(),"!!ALARM CANCELLED!!",Toast.LENGTH_LONG).show();
                        holder.sw.setText("OFF");
                        currentitem.setState(0);
                        int code= currentitem.getId();
                        Intent intent = new Intent(buttonView.getContext(), reciver1.class);
                        pendingIntent1 = PendingIntent.getBroadcast(buttonView.getContext(), code, intent, 0);

                        alarmManager.cancel(pendingIntent1);
                    }


            }
        });
        holder.id.setText(""+(currentitem.getId()));
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }
    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }
}
