package com.example.gamepark;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
public class StartMusic extends Service {
    MediaPlayer mediaPlayer;
    SharedPreferences sp;
    int songid;

    public  StartMusic(){

    }





    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sp= getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        //createNotifChannel();

        songid=sp.getInt("song",0);

        mediaPlayer = MediaPlayer.create(this, songid);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return startId;
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();


    }




    @Override
    public void onLowMemory() {
    }


}
