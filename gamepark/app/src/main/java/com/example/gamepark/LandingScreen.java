package com.example.gamepark;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class LandingScreen extends Fragment {



    public LandingScreen() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing_screen, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController=Navigation.findNavController(view);

        Button start=view.findViewById(R.id.new_deck_btn);
        start.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate((R.id.action_landingScreen_to_newDeck)); //move to next page

            }
        });
    }
}