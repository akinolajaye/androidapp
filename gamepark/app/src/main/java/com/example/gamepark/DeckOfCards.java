package com.example.gamepark;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class DeckOfCards extends Fragment {



    private ArrayList<Bitmap> card_img=new ArrayList<>();


    public DeckOfCards() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck_of_cards, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CustomAdapter customAdapter;
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.card_deck_view);
        DBHandler myDB=new DBHandler(getContext());

        displayData();
        customAdapter=new CustomAdapter(getContext(),card_img);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));


        FloatingActionButton add_new_card_btn= (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        add_new_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_deckOfCards_to_createCard));//move to the next page
            }
        });

        Button play=(Button) view.findViewById(R.id.play_btn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_deckOfCards_to_battlefront));//move to the next page

            }
        });



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