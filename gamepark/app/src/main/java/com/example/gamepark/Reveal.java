package com.example.gamepark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class Reveal extends PopupWindow {

    int card_back_resource;
    Drawable card_back;
    LayoutInflater inflater  ;
    NavController navController;


    public Reveal(View contentView, int width, int height, boolean focusable,
                  Player p1, Player p2, int p1_position, int p2_position ,
                  Player next_player, BattleAdapter battleAdapter, RecyclerView recyclerView,NavController navController){
        super(contentView,width,height,focusable);
        inflater=(LayoutInflater)  contentView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.navController=navController;

        card_back_resource=contentView.getContext().getResources().getIdentifier("@drawable/background_gp_splash",
                null, contentView.getContext().getPackageName());

        card_back = contentView.getContext().getResources().getDrawable(card_back_resource);

        TextView result_lbl=contentView.findViewById(R.id.result_title);

        ImageView p1_reveal = contentView.findViewById(R.id.p1_reveal);
        p1_reveal.setImageBitmap(p1.playing_card.char_card);

        ImageView p2_reveal = contentView.findViewById(R.id.p2_reveal);
        p2_reveal.setImageBitmap(p2.playing_card.char_card);

        ImageView winner_img =contentView.findViewById(R.id.winner_reveal);

        Button continue_btn = contentView.findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                View switch_turn = inflater.inflate(R.layout.switch_turn,null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                        false,next_player,battleAdapter,recyclerView);

                switchTurn.showAtLocation(view, Gravity.CENTER,0,0);
            }
        });


        if (p1.playing_stat>p2.playing_stat){

            winner_img.setImageBitmap(p1.playing_card.char_card);
            p1.Deck.add(p2.playing_card);
            p2.Deck.remove(p2_position);

            if (p2.Deck.size()<1){

                continue_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        View switch_turn = inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                true,p1,navController);

                        switchTurn.showAtLocation(view, Gravity.CENTER,0,0);
                    }
                });


            }


        }else if(p1.playing_stat<p2.playing_stat){

            winner_img.setImageBitmap(p2.playing_card.char_card);
            p2.Deck.add(p1.playing_card);
            p1.Deck.remove(p1_position);

            if (p1.Deck.size()<1){

                continue_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        View switch_turn = inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                true,p2,navController);

                        switchTurn.showAtLocation(view, Gravity.CENTER,0,0);
                    }
                });


            }
        }else{

            winner_img.setImageDrawable(card_back);
            result_lbl.setText("Draw");

        }

    }

}
