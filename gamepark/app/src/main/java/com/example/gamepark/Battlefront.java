package com.example.gamepark;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Battlefront extends Fragment {

    private ArrayList<Bitmap> card_img=new ArrayList<>();
    private ArrayList<String> card_stat1=new ArrayList<>();
    private ArrayList<String> card_stat2=new ArrayList<>();
    private ArrayList<String> card_stat3=new ArrayList<>();
    private ArrayList<String> card_stat4=new ArrayList<>();
    private ArrayList<String> card_stat5=new ArrayList<>();
    private ArrayList<String> card_stat6=new ArrayList<>();
    Player jaye=new Player("jaye");



    public Battlefront() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battlefront, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView p1=(ImageView) view.findViewById(R.id.player1);

        BattleAdapter battleAdapter;
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.battlefront_deck_view);

        addAllCardsToDeck();

        battleAdapter=new BattleAdapter(getContext(), jaye.Deck,
                p1);


        recyclerView.setAdapter(battleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));

    }

    public void addAllCardsToDeck(){
        SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");

        DBHandler myDB=new DBHandler(getContext());
        Cursor cursor= myDB.getDeck(current_deck_name);
        if(cursor.getCount()==0){


        }else{
            cursor.moveToFirst();
            do {
                jaye.addCardToDeck(myDB.getImage(cursor.getBlob(1)),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7)),
                        Integer.parseInt(cursor.getString(8))

                        );
                /*card_img.add(myDB.getImage(cursor.getBlob(1)));
                card_stat1.add(cursor.getString(2));
                card_stat2.add(cursor.getString(3));
                card_stat3.add(cursor.getString(4));
                card_stat4.add(cursor.getString(5));
                card_stat5.add(cursor.getString(6));
                card_stat6.add(cursor.getString(7));*/



            }while (cursor.moveToNext());
        }
    }



}


