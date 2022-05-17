package com.example.gamepark;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RemindPlay extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyGamePark")
                .setSmallIcon(R.drawable.background_gp_splash)
                .setContentTitle("GAMEPARK")
                .setContentText("Hey you havent finished your game")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());

    }
}
