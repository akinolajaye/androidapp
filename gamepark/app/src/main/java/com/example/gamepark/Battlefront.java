package com.example.gamepark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Battlefront extends Fragment {

    Player jaye=new Player("jaye");
    Player chris=new Player("chris");
    public RecyclerView recyclerView;

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
    public void onPause() {
        super.onPause();
        getContext().startService(new Intent(getContext(),Reminder.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().stopService(new Intent(getContext(),Reminder.class));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView p1_playing_card=(ImageView) view.findViewById(R.id.player1);
        ImageView p2_playing_card=view.findViewById(R.id.player2);

        TextView stat_title=view.findViewById(R.id.stat_title);
        Button reveal_btn=view.findViewById(R.id.reveal_btn);
        new PlayBackSound(getContext(),R.raw.battle);

        setUpDeck(jaye,chris);
;
        BattleAdapter battleAdapter;
        recyclerView=(RecyclerView) view.findViewById(R.id.battlefront_deck_view);
        NavController navController= Navigation.findNavController(view);
        battleAdapter=new BattleAdapter(getContext(), jaye,p1_playing_card,stat_title,chris,recyclerView,p2_playing_card,reveal_btn,navController);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));
        recyclerView.setAdapter(battleAdapter);
        

    }

    public void setUpDeck(Player player1,Player player2){
        SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");
        DBHandler myDB=new DBHandler(getContext());
        Cursor cursor= myDB.getDeck(current_deck_name);
        ArrayList<Card> player1_deck =new ArrayList<>();
        ArrayList<Card> player2_deck =new ArrayList<>();

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

            }while (cursor.moveToNext());

            Log.i("TAG",Deck.toString());
            Collections.shuffle(Deck);
            int deck_size=Deck.size();
            //Log.i("TAG",Integer.toString(deck_size));
            if ((deck_size % 2)==0){

                for(int i=0;i<deck_size/2;i++){

                    player1_deck.add(Deck.get(i));


                }
                for(int i=deck_size/2;i<deck_size;i++){

                    player2_deck.add(Deck.get(i));

                }

            }else{

                for(int i=0;i<(deck_size+1)/2;i++){

                    player1_deck.add(Deck.get(i));


                }
                for(int i=(deck_size+1)/2;i<deck_size;i++){

                    player2_deck.add(Deck.get(i));


                }
            }

            player1.Deck=player1_deck;
            player2.Deck=player2_deck;

        }
    }



}


