package com.example.gamepark;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewDeck extends Fragment {
    String name_of_deck,stat1_val,stat2_val,stat3_val,stat4_val,stat5_val,stat6_val;
    EditText deck_name,stat1,stat2,stat3,stat4,stat5,stat6;
    Button submit_btn;

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

        // store the xml view objects as variables
        deck_name=(EditText) view.findViewById(R.id.deck_name);
        stat1=(EditText) view.findViewById(R.id.stat_1);
        stat2=(EditText) view.findViewById(R.id.stat_2);
        stat3=(EditText) view.findViewById(R.id.stat_3);
        stat4=(EditText) view.findViewById(R.id.stat_4);
        stat5=(EditText) view.findViewById(R.id.stat_5);
        stat6=(EditText) view.findViewById(R.id.stat_6);
        sp= getContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);

        submit_btn= (Button) view.findViewById(R.id.submit);
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

                /*getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"Made", Toast.LENGTH_SHORT).show();


                    }
                });*/







               DBHandler myDB= new DBHandler(getContext());



                //myDB.newDeck(name_of_deck,stat1_val,stat2_val, //add table to database
                        //stat3_val,stat4_val,stat5_val,stat6_val);



                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_newDeck_to_deckOfCards));












            }

        });

    }
}