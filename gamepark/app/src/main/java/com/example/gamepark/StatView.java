package com.example.gamepark;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import org.jetbrains.annotations.Nullable;

public class StatView extends PopupWindow {

    Button stat1,stat2,stat3,stat4,stat5,stat6;



    public StatView(View contentView, int width, int height,boolean focusable, Bitmap stat_img){
        super(contentView,width,height,focusable);

        SharedPreferences sp=contentView.getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");

        ImageView stat_view =contentView.findViewById(R.id.stat_view);
        stat_view.setImageBitmap(stat_img);


        Cursor stats=new DBHandler(contentView.getContext()).getDeckStats(current_deck_name);
        stats.moveToNext();
        stats.moveToNext();
        stats.moveToNext();

        stat1 = contentView.findViewById(R.id.stat1_btn);
        stat1.setText(stats.getString(1));
        stats.moveToNext();
        stat2 = contentView.findViewById(R.id.stat2_btn);
        stat2.setText(stats.getString(1));
        stats.moveToNext();
        stat3 = contentView.findViewById(R.id.stat3_btn);
        stat3.setText(stats.getString(1));
        stats.moveToNext();
        stat4 = contentView.findViewById(R.id.stat4_btn);
        stat4.setText(stats.getString(1));
        stats.moveToNext();
        stat5 = contentView.findViewById(R.id.stat5_btn);
        stat5.setText(stats.getString(1));
        stats.moveToNext();
        stat6 = contentView.findViewById(R.id.switch_btn);
        stat6.setText(stats.getString(1));


    }

    public StatView(View contentView, int width, int height, boolean focusable, Bitmap stat_img, @Nullable int any) {
        super(contentView, width, height, focusable);

        SharedPreferences sp = contentView.getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name = sp.getString("deck_name", "");

        ImageView stat_view = contentView.findViewById(R.id.return_stat_view);
        stat_view.setImageBitmap(stat_img);

    }
}
