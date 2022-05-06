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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DefenceAdapter extends RecyclerView.Adapter<DefenceAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Card> deck;
    public ImageView playing_card_icon;
    public Player player;
    Button choose_btn;
    LayoutInflater inflater  ;
    public String chosen_stat;

    DefenceAdapter(Context context,Player player ,ImageView playing_card_icon,String chosen_stat){
        this.context=context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.player=player;
        this.deck=player.Deck;
        this.playing_card_icon=playing_card_icon;
        this.chosen_stat=chosen_stat;

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
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final StatView viewStatWindow = new StatView(stat_popup, width, height, true, (Bitmap) deck.get(position).char_card,0);
                // show the popup window
                viewStatWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                choose_btn = stat_popup.findViewById(R.id.return_card);
                choose_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.playing_stat=deck.get(position).getChosenStat(chosen_stat);
                        viewStatWindow.dismiss();
                        playing_card_icon.setImageBitmap(deck.get(position).char_card);

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

