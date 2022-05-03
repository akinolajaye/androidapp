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
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class DeckOfCards extends Fragment {
    RecyclerView recyclerView;

    DBHandler myDB;

    ArrayList<Bitmap> card_img;
    ArrayList<String> card_name;
    SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
    String current_deck_name=sp.getString("deck_name","");


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

        Button add_new_card_btn= (Button) view.findViewById(R.id.floatingActionButton);
        add_new_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_deckOfCards_to_createCard));//move to the next page
            }
        });

        myDB=new DBHandler(getContext());
        card_img=new ArrayList<>();
        card_name= new ArrayList<>();

        displayData();
    }

    public void displayData(){
        Cursor cursor= myDB.getDeck(current_deck_name);
        if(cursor.getCount()==0){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"No data", Toast.LENGTH_SHORT).show();


                }
            });


        }else{
            while (cursor.moveToNext()){

                card_img.add(myDB.getImage(cursor.getBlob(1)));
                card_name.add(cursor.getString(2));

            }
        }
    }
}