package com.example.gamepark;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

public class Reminder extends Service {
    public Reminder() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


       //Toast.makeText(getApplicationContext().,"exited app",Toast.LENGTH_SHORT).show();
        createNotifChannel();

        Intent remind_intent = new Intent(Reminder.this,RemindPlay.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this,0,remind_intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtExit= System.currentTimeMillis();

        long wait=100*10;
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtExit+wait,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotifChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name ="ReminderChannel";
            String desc="Channel for gamepark reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyGamePark",name,importance);
            channel.setDescription(desc);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);




        }
    }
}