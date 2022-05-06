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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Battlefront extends Fragment {

    Player jaye=new Player("jaye");
    Player chris=new Player("chris");
    private RecyclerView recyclerView;

    public String chosen_stat;




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

        ImageView p1_playing_card=(ImageView) view.findViewById(R.id.player1);
        ImageView p2_playing_card=view.findViewById(R.id.player2);

        Button next_play=view.findViewById(R.id.next_play_btn);
        TextView stat_title=view.findViewById(R.id.stat_title);
        addAllCardsToDeck(jaye);
        addAllCardsToDeck(chris);


        BattleAdapter battleAdapter;
        recyclerView=(RecyclerView) view.findViewById(R.id.battlefront_deck_view);

        battleAdapter=new BattleAdapter(getContext(), jaye,p1_playing_card,stat_title);

        recyclerView.setAdapter(battleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));

        next_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DefenceAdapter defenceAdapter;
                defenceAdapter =new DefenceAdapter(getContext(), chris,p2_playing_card, battleAdapter.chosen_stat);
                recyclerView.setAdapter(defenceAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));

            }
        });

    }

    public void addAllCardsToDeck(Player player){
        SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");
        DBHandler myDB=new DBHandler(getContext());
        Cursor cursor= myDB.getDeck(current_deck_name);

        ArrayList<Card> Deck=new ArrayList<>();






        if(cursor.getCount()==0){


        }else{
            cursor.moveToFirst();
            do {

                Deck.add(new Card(myDB.getImage(cursor.getBlob(1)),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7)),
                        Integer.parseInt(cursor.getString(8))));




                player.addCardToDeck(myDB.getImage(cursor.getBlob(1)),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7)),
                        Integer.parseInt(cursor.getString(8))

                        );
            }while (cursor.moveToNext());


            Collections.shuffle(Deck);

            


        }
    }



}


