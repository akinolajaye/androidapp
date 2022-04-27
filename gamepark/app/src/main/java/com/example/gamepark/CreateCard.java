package com.example.gamepark;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        //deck_lbl.setText("Anime");

        DBHandler myDB = new DBHandler (getContext());
        Cursor cursor = myDB.getDeckStats();

        cursor.moveToNext();

        for(int i=0;i<card_lbl.size();i++){


            card_lbl.get(i).setText(cursor.getString(1));
            cursor.moveToNext();
        }





    }
}