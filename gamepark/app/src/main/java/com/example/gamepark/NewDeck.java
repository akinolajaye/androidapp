package com.example.gamepark;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class NewDeck extends Fragment {
    String name_of_deck,stat1_val,stat2_val,stat3_val,stat4_val,stat5_val,stat6_val;
    EditText deck_name,stat1,stat2,stat3,stat4,stat5,stat6;
    ImageView submit_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_deck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sp;
        setupUI(view.findViewById(R.id.new_deck_view));

        // store the xml view objects as variables
        deck_name=(EditText) view.findViewById(R.id.deck_name);
        stat1=(EditText) view.findViewById(R.id.stat_1);
        stat2=(EditText) view.findViewById(R.id.stat_2);
        stat3=(EditText) view.findViewById(R.id.stat_3);
        stat4=(EditText) view.findViewById(R.id.stat_4);
        stat5=(EditText) view.findViewById(R.id.stat_5);
        stat6=(EditText) view.findViewById(R.id.stat_6);
        sp= getContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);

        submit_btn= view.findViewById(R.id.submit_img);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //store the values of the edit text wich will be use as columns for the database
                name_of_deck=deck_name.getText().toString();
                stat1_val=stat1.getText().toString();
                stat2_val=stat2.getText().toString();
                stat3_val=stat3.getText().toString();
                stat4_val=stat4.getText().toString();
                stat5_val=stat5.getText().toString();
                stat6_val=stat6.getText().toString();

                SharedPreferences.Editor editor=sp.edit();
                editor.putString("deck_name",name_of_deck);
                editor.commit();


                if(name_of_deck.matches("")
                        || stat1_val.matches("")
                        || stat2_val.matches("")
                        || stat3_val.matches("")
                        || stat4_val.matches("")
                        || stat5_val.matches("")
                        || stat6_val.matches("")){
                    Toast.makeText(getContext(), "Not All Entries Filled", Toast.LENGTH_SHORT).show();
                }else {
                    DBHandler myDB= new DBHandler(getContext());

                    try{

                        myDB.newDeck(name_of_deck,stat1_val,stat2_val, //add table to database
                                stat3_val,stat4_val,stat5_val,stat6_val);

                        NavController navController= Navigation.findNavController(view);
                        navController.navigate((R.id.action_newDeck_to_deckOfCards));

                    }catch (Exception e){

                        Toast.makeText(getContext(), "Database Exists", Toast.LENGTH_SHORT).show();
                    }







                }






















            }

        });

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }

    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }




    }






}