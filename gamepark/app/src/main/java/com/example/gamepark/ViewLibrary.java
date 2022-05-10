package com.example.gamepark;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ViewLibrary extends Fragment {

    ArrayList<String> decks;
    RecyclerView recyclerView;



    public ViewLibrary() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        decks= new ArrayList<>();

        DBHandler myDB=new DBHandler(getContext());
        Cursor cursor = myDB.getDecksName();

        do {
            decks.add(cursor.getString(0));
        }while (cursor.moveToNext());


        NavController navController= Navigation.findNavController(view);
        recyclerView=(RecyclerView) view.findViewById(R.id.view_deck_lib);
        LibraryAdapter libraryAdapter=new LibraryAdapter(getContext(),decks,navController);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));
        recyclerView.setAdapter(libraryAdapter);
    }
}