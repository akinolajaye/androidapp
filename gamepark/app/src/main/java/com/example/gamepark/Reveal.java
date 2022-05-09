package com.example.gamepark;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class Reveal extends PopupWindow {

    public Reveal(View contentView, int width, int height, boolean focusable,
                      Player p1,Player p2,int p1_position, int p2_position ){
        super(contentView,width,height,focusable);

        ImageView p1_reveal = contentView.findViewById(R.id.p1_reveal);
        p1_reveal.setImageBitmap(p1.playing_card.char_card);

        ImageView p2_reveal = contentView.findViewById(R.id.p2_reveal);
        p2_reveal.setImageBitmap(p2.playing_card.char_card);

        ImageView winner_img =contentView.findViewById(R.id.winner_reveal);


        if (p1.playing_stat>p2.playing_stat){

            winner_img.setImageBitmap(p1.playing_card.char_card);
            p1.Deck.add(p2.playing_card);
            p2.Deck.remove(p2_position);


        }else if(p1.playing_stat<p2.playing_stat){

            winner_img.setImageBitmap(p2.playing_card.char_card);
            p2.Deck.add(p1.playing_card);
            p1.Deck.remove(p1_position);
        }else{

        }

    }

}
