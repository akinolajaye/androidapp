package com.example.gamepark;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleAdapter extends RecyclerView.Adapter<BattleAdapter.MyViewHolder> {

    private Context context;
    private ArrayList card_img;
    public ImageView p1_card;
    public int position;
    BattleAdapter(Context context, ArrayList card_img, ImageView p1_card){

        this.context=context;
        this.card_img=card_img;
        this.p1_card=p1_card;




    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BattleAdapter.MyViewHolder holder, int position) {
        this.position=position;
        holder.card_img_deck.setImageBitmap((Bitmap) card_img.get(position));

        holder.card_img_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1_card.setImageBitmap((Bitmap) card_img.get(position));

                /*Bitmap bitmap= Bitmap.createBitmap(holder.playing_card.getWidth(),holder.playing_card.getHeight(), Bitmap.Config.ARGB_8888);//create bitmap object
                Canvas canvas = new Canvas(bitmap);//create canvas obejct using bitmap
                holder.playing_card.draw(canvas);//draw to relative layout to store the xml layout as an image

                ImageView p1= view.findViewById(R.id.player1);
                p1.setImageBitmap(bitmap);*/


            }
        });

        //holder.card_name_deck.setText(card_name.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return card_img.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView card_img_deck;
        RelativeLayout playing_card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //card_name_deck=itemView.findViewById(R.id.card_deck_char_name);
            card_img_deck=itemView.findViewById(R.id.card_img_deck);
            playing_card=itemView.findViewById(R.id.playing_card);

        }
    }
}

