package com.example.gamepark;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class CreateCard extends Fragment {
    ActivityResultLauncher<String> char_pic;
    private ArrayList<TextView> card_lbl= new ArrayList<>();
    private ArrayList<EditText> card_stats= new ArrayList<>();
    private  String char_icon;




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





        card_lbl.add((TextView) view.findViewById(R.id.char_img_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.char_name_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat1_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat2_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat3_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat4_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat5_lbl));
        card_lbl.add((TextView) view.findViewById(R.id.stat6_lbl));
        TextView deck_lbl=(TextView) view.findViewById(R.id.deck_name_lbl);

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


        deck_lbl.setText("Anime");
        for(int i=0;i<card_lbl.size();i++){
            card_lbl.get(i).setText(cursor.getString(1));
            cursor.moveToNext();
        }

        char_pic=registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        ImageView img=(ImageView) view.findViewById(R.id.char_img);
                        img.setImageURI(result);
                        char_icon=result.toString();



                    }
                }
        );

        Button get_image= (Button) view.findViewById(R.id.upload);
        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char_pic.launch("image/*");


            }
        });

        Button addCard = (Button) view.findViewById(R.id.add_char);
        addCard.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {







                DBHandler myDB = new DBHandler (getContext());

                /*myDB.addCardToDeck(deck_lbl.getText().toString(),
                        card_lbl.get(0).getText().toString(),char_icon,
                        card_lbl.get(1).getText().toString(),card_stats.get(0).getText().toString(),
                        card_lbl.get(2).getText().toString(),card_stats.get(1).getText().toString(),
                        card_lbl.get(3).getText().toString(),card_stats.get(2).getText().toString(),
                        card_lbl.get(4).getText().toString(),card_stats.get(3).getText().toString(),
                        card_lbl.get(5).getText().toString(),card_stats.get(4).getText().toString(),
                        card_lbl.get(6).getText().toString(),card_stats.get(5).getText().toString(),
                        card_lbl.get(7).getText().toString(),card_stats.get(6).getText().toString()
                        );*/

                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_createCard_to_viewCard));
            }

        });

    }









}