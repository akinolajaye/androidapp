package com.example.gamepark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleAdapter extends RecyclerView.Adapter<BattleAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Card> deck;
    public ImageView playing_card_icon,next_player_icon;
    TextView stat_title;
    public Player player;
    public  Player next_player;
    Button stat1_btn,stat2_btn,stat3_btn,stat4_btn,stat5_btn,stat6_btn;
    ImageView reveal_btn;
    LayoutInflater  inflater  ;
    public String chosen_stat;
    RecyclerView recyclerView;
    int card_back_resource;
    Drawable card_back;
    NavController navController;



    BattleAdapter(Context context, Player player , ImageView playing_card_icon,
                  TextView stat_title, Player next_player, RecyclerView recyclerView,
                  ImageView next_player_icon, ImageView reveal_btn, NavController navController){
        this.context=context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.player=player;
        this.deck=player.Deck;
        this.playing_card_icon=playing_card_icon;
        this.stat_title=stat_title;
        this.next_player=next_player;
        this.recyclerView=recyclerView;
        this.next_player_icon=next_player_icon;
        this.reveal_btn=reveal_btn;
        this.navController=navController;
        reveal_btn.setEnabled(false);

        card_back_resource=context.getResources().getIdentifier("@drawable/background_gp_splash",
                null, context.getPackageName());

        card_back = context.getResources().getDrawable(card_back_resource);








    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BattleAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //this.position=position;
        holder.card_img_deck.setImageBitmap((Bitmap) deck.get(position).char_card);

        holder.card_img_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View stat_popup=inflater.inflate(R.layout.card_expand_choose,null);
                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                final StatView viewStatWindow = new StatView(stat_popup, width, height,true,(Bitmap) deck.get(position).char_card);
                // show the popup window
                viewStatWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                stat1_btn = stat_popup.findViewById(R.id.stat1_btn);
                stat1_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).stat1;
                        chosen_stat="stat1";
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageDrawable(card_back);
                        player.playing_card=deck.get(position);
                        player.card_position=position;
                        stat_title.setText(stat1_btn.getText());

                        DefenceAdapter defenceAdapter = new DefenceAdapter(context, next_player,
                                next_player_icon,stat_title,player,recyclerView,
                                playing_card_icon,chosen_stat,reveal_btn,navController);
                        View switch_turn =inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                false,next_player,defenceAdapter,recyclerView);

                        switchTurn.showAtLocation(view,Gravity.CENTER,0,0);




                    }
                });


                stat2_btn = stat_popup.findViewById(R.id.stat2_btn);
                stat2_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).stat2;
                        chosen_stat="stat2";
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageDrawable(card_back);
                        stat_title.setText(stat2_btn.getText());
                        player.playing_card=deck.get(position);
                        player.card_position=position;


                        View switch_turn =inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        DefenceAdapter defenceAdapter = new DefenceAdapter(context,
                                next_player, next_player_icon,stat_title,player,recyclerView,playing_card_icon,chosen_stat,reveal_btn,navController);
                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                false,next_player,defenceAdapter,recyclerView);

                        switchTurn.showAtLocation(view,Gravity.CENTER,0,0);

                    }
                });

                stat3_btn = stat_popup.findViewById(R.id.stat3_btn);
                stat3_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).stat3;
                        chosen_stat="stat3";
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageDrawable(card_back);
                        player.playing_card=deck.get(position);
                        player.card_position=position;
                        stat_title.setText(stat3_btn.getText());


                        View switch_turn =inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        DefenceAdapter defenceAdapter = new DefenceAdapter(context, next_player,
                                next_player_icon,stat_title,player,recyclerView,playing_card_icon,chosen_stat,reveal_btn,navController);
                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                false,next_player,defenceAdapter,recyclerView);

                        switchTurn.showAtLocation(view,Gravity.CENTER,0,0);


                    }
                });
                stat4_btn = stat_popup.findViewById(R.id.stat4_btn);
                stat4_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).stat4;
                        chosen_stat="stat4";
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageDrawable(card_back);
                        player.playing_card=deck.get(position);
                        player.card_position=position;
                        stat_title.setText(stat4_btn.getText());


                        View switch_turn =inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        DefenceAdapter defenceAdapter = new DefenceAdapter(context, next_player,
                                next_player_icon,stat_title,player,recyclerView,playing_card_icon,chosen_stat,reveal_btn,navController);
                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                false,next_player,defenceAdapter,recyclerView);

                        switchTurn.showAtLocation(view,Gravity.CENTER,0,0);


                    }
                });
                stat5_btn = stat_popup.findViewById(R.id.stat5_btn);
                stat5_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).stat5;
                        chosen_stat="stat5";
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageDrawable(card_back);
                        player.playing_card=deck.get(position);
                        player.card_position=position;
                        stat_title.setText(stat5_btn.getText());


                        View switch_turn =inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        DefenceAdapter defenceAdapter = new DefenceAdapter(context, next_player,
                                next_player_icon,stat_title,player,recyclerView,playing_card_icon,chosen_stat,reveal_btn,navController);
                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                false,next_player,defenceAdapter,recyclerView);

                        switchTurn.showAtLocation(view,Gravity.CENTER,0,0);


                    }
                });
                stat6_btn = stat_popup.findViewById(R.id.switch_btn);
                stat6_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).stat6;
                        chosen_stat="stat6";
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageDrawable(card_back);
                        player.playing_card=deck.get(position);
                        player.card_position=position;
                        stat_title.setText(stat6_btn.getText());


                        View switch_turn =inflater.inflate(R.layout.switch_turn,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        DefenceAdapter defenceAdapter = new DefenceAdapter(context, next_player,
                                next_player_icon,stat_title,player,recyclerView,playing_card_icon,chosen_stat,reveal_btn,navController);
                        final SwitchTurn switchTurn = new SwitchTurn(switch_turn, width, height,
                                false,next_player,defenceAdapter,recyclerView);

                        switchTurn.showAtLocation(view,Gravity.CENTER,0,0);


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
            card_img_deck=itemView.findViewById(R.id.deck_img);


        }
    }


}

