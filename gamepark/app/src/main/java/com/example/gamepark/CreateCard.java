package com.example.gamepark;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateCard extends Fragment {


    public CreateCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_card, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        ArrayList<TextView> card_lbl= new ArrayList<>();


        card_lbl.add((TextView) view.findViewById(R.id.char_img_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.char_name_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat1_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat2_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat3_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat4_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat5_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat6_lbl));
        TextView deck_lbl=(TextView) view.findViewById(R.id.deck_name_lbl);
        deck_lbl.setText("Anime");


        ArrayList<EditText> card_stats= new ArrayList<>();
        card_stats.add((EditText) view.findViewById(R.id.char_name));
        card_stats.add((EditText) view.findViewById(R.id.stat1));
        card_stats.add((EditText) view.findViewById(R.id.stat2));
        card_stats.add((EditText) view.findViewById(R.id.stat3));
        card_stats.add((EditText) view.findViewById(R.id.stat4));
        card_stats.add((EditText) view.findViewById(R.id.stat5));
        card_stats.add((EditText) view.findViewById(R.id.stat6));



        DBHandler myDB = new DBHandler (getContext());
        Cursor cursor = myDB.getDeckStats();

        cursor.moveToNext();

        for(int i=0;i<card_lbl.size();i++){
            card_lbl.get(i).setText(cursor.getString(1));
            cursor.moveToNext();
        }

        Button addCard = (Button) view.findViewById(R.id.add_char);
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler myDB = new DBHandler (getContext());


                myDB.addCardToDeck(deck_lbl.getText().toString(),
                        card_lbl.get(1).getText().toString(),card_stats.get(0).getText().toString(),
                        card_lbl.get(2).getText().toString(),card_stats.get(1).getText().toString(),
                        card_lbl.get(3).getText().toString(),card_stats.get(2).getText().toString(),
                        card_lbl.get(4).getText().toString(),card_stats.get(3).getText().toString(),
                        card_lbl.get(5).getText().toString(),card_stats.get(4).getText().toString(),
                        card_lbl.get(6).getText().toString(),card_stats.get(5).getText().toString(),
                        card_lbl.get(7).getText().toString(),card_stats.get(6).getText().toString()
                        );



            }

        });





    }
}