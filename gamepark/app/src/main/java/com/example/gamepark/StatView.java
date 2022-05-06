package com.example.gamepark;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class StatView extends PopupWindow {


    StatView(View contentView, int width, int height,boolean focusable, Bitmap stat_img){
        super(contentView,width,height,focusable);

        ImageView stat_view =contentView.findViewById(R.id.stat_view);
        stat_view.setImageBitmap(stat_img);


    }
}
