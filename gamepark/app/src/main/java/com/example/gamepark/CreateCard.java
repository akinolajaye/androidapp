package com.example.gamepark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    ActivityResultLauncher<String> char_pic;//variable for activity launcher to select image
    private ArrayList<TextView> card_lbl= new ArrayList<>(); //array list to hold all text view objects for label
    private ArrayList<EditText> card_stats= new ArrayList<>();//array list to hold all edit view objects for entry
    private  String char_icon; //string to hold image uri


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

        SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");

        //this code adds all the text view and edit view objects into the arrays defined above
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

        DBHandler myDB = new DBHandler (getContext());// makes an instance of our db handler and connects to the database
        Cursor cursor = myDB.getDeckStats(current_deck_name);//calls method thst returns  list of all the labels (columns of the table)
        cursor.moveToNext();//skips the id column

        deck_lbl.setText(current_deck_name);
        for(int i=0;i<card_lbl.size();i++){
            card_lbl.get(i).setText(cursor.getString(1));//sets the label to the value gotten from the database
            cursor.moveToNext();
        }

        char_pic=registerForActivityResult(//function to open photo app to select image
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        ImageView img=(ImageView) view.findViewById(R.id.char_img);//get image view object
                        img.setImageURI(result);//set the imageview to the photo selected
                        char_icon=result.toString(); //store the uri of the image



                    }
                }
        );

        Button get_image= (Button) view.findViewById(R.id.upload);//get button object to upload an image
        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char_pic.launch("image/*");//call the charpic function


            }
        });

        Button addCard = (Button) view.findViewById(R.id.add_char);//listener for button to add character
        addCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                //store the label and the entry for a card in a bundle
                Bundle bundle = new Bundle();

                bundle.putString("char_icon",char_icon);
                bundle.putString("char_name",card_stats.get(0).getText().toString());
                bundle.putString("stat1_lbl",card_lbl.get(2).getText().toString());
                bundle.putString("stat1",card_stats.get(1).getText().toString());
                bundle.putString("stat2_lbl",card_lbl.get(3).getText().toString());
                bundle.putString("stat2",card_stats.get(2).getText().toString());
                bundle.putString("stat3_lbl",card_lbl.get(4).getText().toString());
                bundle.putString("stat3",card_stats.get(3).getText().toString());
                bundle.putString("stat4_lbl",card_lbl.get(5).getText().toString());
                bundle.putString("stat4",card_stats.get(4).getText().toString());
                bundle.putString("stat5_lbl",card_lbl.get(6).getText().toString());
                bundle.putString("stat5",card_stats.get(5).getText().toString());
                bundle.putString("stat6_lbl",card_lbl.get(7).getText().toString());
                bundle.putString("stat6",card_stats.get(6).getText().toString());


                getParentFragmentManager().setFragmentResult("card_info",bundle);

                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_createCard_to_viewCard));//move to the next page
            }

        });

    }









}