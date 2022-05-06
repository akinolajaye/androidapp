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
        displayData();
        battleAdapter=new BattleAdapter(getContext(),card_img,p1);
        recyclerView.setAdapter(battleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));

    }

    public void displayData(){
        SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");

        DBHandler myDB=new DBHandler(getContext());
        Cursor cursor= myDB.getDeck(current_deck_name);
        if(cursor.getCount()==0){


        }else{
            cursor.moveToFirst();
            do {

                card_img.add(myDB.getImage(cursor.getBlob(1)));


            }while (cursor.moveToNext());
        }
    }



}


