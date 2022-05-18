package com.example.gamepark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


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
        Log.d("TAG", Integer.toString(Build.VERSION.SDK_INT));
        NavController navController=Navigation.findNavController(view);

        ImageView start=view.findViewById(R.id.new_deck_img);
        start.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate((R.id.action_landingScreen_to_newDeck)); //move to next page

            }
        });

        ImageView view_lib=view.findViewById(R.id.view_lib_img);
        view_lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate((R.id.action_landingScreen_to_viewLibrary)); //move to next page

            }
        });

        ImageView logout=view.findViewById(R.id.logout_img);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext().getApplicationContext(),Login.class));
            }
        });


        FloatingActionButton floatingActionButton=view.findViewById(R.id.userguide);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext().getApplicationContext(),UserGuide.class));

            }
        });
    }
}