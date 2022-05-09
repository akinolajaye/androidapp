package com.example.gamepark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DefenceAdapter extends RecyclerView.Adapter<DefenceAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Card> deck;
    public ImageView playing_card_icon,next_player_icon;
    public Player player,next_player;
    Button choose_btn,reveal_btn;
    LayoutInflater inflater  ;
    public String chosen_stat;
    TextView stat_title;
    RecyclerView recyclerView;


    DefenceAdapter(Context context, Player player , ImageView playing_card_icon, TextView stat_title, Player next_player, RecyclerView recyclerView,
                   ImageView next_player_icon,String chosen_stat, Button reveal_btn){
        this.context=context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.player=player;
        this.deck=player.Deck;
        this.playing_card_icon=playing_card_icon;
        this.chosen_stat=chosen_stat;
        this.stat_title=stat_title;
        this.next_player=next_player;
        this.recyclerView = recyclerView;
        this.next_player_icon=next_player_icon;
        this.reveal_btn=reveal_btn;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new DefenceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefenceAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //this.position=position;
        holder.card_img_deck.setImageBitmap((Bitmap) deck.get(position).char_card);

        holder.card_img_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View stat_popup = inflater.inflate(R.layout.choose_return_card, null);
                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                final StatView viewStatWindow = new StatView(stat_popup, width, height, true, (Bitmap) deck.get(position).char_card,0);

                viewStatWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                choose_btn = stat_popup.findViewById(R.id.return_card);
                choose_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).getChosenStat(chosen_stat);
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageBitmap(deck.get(position).char_card);
                        player.playing_card=deck.get(position);
                        player.card_position=position;


                        reveal_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                View reveal_popup=inflater.inflate(R.layout.reveal,null);
                                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                                Reveal reveal= new Reveal(reveal_popup,width,height,true,player,next_player,player.card_position,next_player.card_position);
                                reveal.showAtLocation(view,Gravity.CENTER,0,0);

                                BattleAdapter battleAdapter = new BattleAdapter(context,player,playing_card_icon,stat_title,next_player,recyclerView,next_player_icon,reveal_btn);
                                recyclerView.setAdapter(battleAdapter);
                            }
                        });


                        //playing_card_icon.setImageResource(android.R.color.transparent);
                        //next_player_icon.setImageResource(android.R.color.transparent);



                    }
                });



            }
        });

    }

    @Override
    public int getItemCount() {
        return deck.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView card_img_deck;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //card_name_deck=itemView.findViewById(R.id.card_deck_char_name);
            card_img_deck=itemView.findViewById(R.id.card_img_deck);


        }
    }
}

