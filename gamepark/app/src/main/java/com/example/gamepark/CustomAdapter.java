package com.example.gamepark;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList card_img;
    public View view;

    CustomAdapter(Context context,ArrayList card_img){

        this.context=context;
        this.card_img=card_img;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.card_img_deck.setImageBitmap((Bitmap) card_img.get(position));

    }

    @Override
    public int getItemCount() {
        return card_img.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView card_img_deck;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_img_deck=itemView.findViewById(R.id.deck_img);


        }
    }
}
