package com.example.gamepark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class PlayBackSound {

    public PlayBackSound(Context context,int song_raw){
        SharedPreferences sp;

        sp= context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("song",song_raw);
        editor.commit();
        Intent intent = new Intent(context,StartMusic.class);
        context.stopService(intent);

        context.startService(intent);
    }

}
