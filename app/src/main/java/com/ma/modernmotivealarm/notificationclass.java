package com.ma.modernmotivealarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;


import android.content.Intent;


import android.net.Uri;
import android.os.Build;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



public class notificationclass extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            scheduleJob(remoteMessage);
        }

    }

    private void scheduleJob(RemoteMessage remoteMessage) {
        String name = remoteMessage.getNotification().getTitle();
        String description = remoteMessage.getNotification().getBody();
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
       String title="Dear user";
        notificationIntent.setData(Uri.parse(name));
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "channel_id")
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle(title) // title for notification
                    .setContentText(description)
                    .setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.reallogo)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
            notificationManager.notify(7897, mBuilder.build());
        }
        else
        {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "channel_id")
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle(title) // title for notification
                    .setContentText(description)
                    .setSmallIcon(R.drawable.reallogo)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(132, mBuilder.build());
        }

    }


}

