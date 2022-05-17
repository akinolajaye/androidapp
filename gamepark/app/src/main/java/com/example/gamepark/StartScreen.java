package com.example.gamepark;

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


public class StartScreen extends Fragment {

    public StartScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController=Navigation.findNavController(view);

        Button start=view.findViewById(R.id.start_btn);
        start.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate((R.id.action_startScreen_to_landingScreen));

            }
        });
    }

    


}