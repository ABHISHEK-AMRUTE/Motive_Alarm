package com.ma.modernmotivealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class reciver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Intent in ;


        in=new Intent(context,wakemeup_youtube.class);
        // if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.M)
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("video_path",intent.getStringExtra("video_path"));
        in.putExtra("switch_id",intent.getIntExtra("switch_id",-1));
        in.putExtra("name",intent.getStringExtra("name"));
        context.startActivity(in);
    }
}
