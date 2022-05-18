package com.example.gamepark;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;


public class DeckOfCards extends Fragment {

    public StorageReference storageReference,deck_ref;
    public FirebaseStorage firebaseStorage;
    public FirebaseAuth firebaseAuth;



    private ArrayList<Bitmap> card_img=new ArrayList<>();


    public DeckOfCards() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck_of_cards, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseStorage= FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        deck_ref= storageReference.child(firebaseAuth.getCurrentUser().getUid());

        CustomAdapter customAdapter;
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.card_deck_view);
        DBHandler myDB=new DBHandler(getContext());

        displayData();
        customAdapter=new CustomAdapter(getContext(),card_img);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 20, true, 0));


        ImageView add_new_card_btn= view.findViewById(R.id.plus_btn);
        add_new_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_deckOfCards_to_createCard));//move to the next page
            }
        });

        ImageView play= view.findViewById(R.id.play_img);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate((R.id.action_deckOfCards_to_battlefront));//move to the next page

            }
        });

        ImageView save_deck= view.findViewById(R.id.save_img);
        save_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDeck(deck_ref);
            }
        });




    }

    private void saveDeck(StorageReference deck_ref) {

        try {


            deck_ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // File deleted successfully

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Uh-oh, an error occurred!

                }
            });
        }catch ( Exception  e){

        };

        File file = new File(Environment.getExternalStorageDirectory(), "Download/decks.db");

        Uri deck_uri=Uri.fromFile(file);

        UploadTask uploadTask = deck_ref.putFile(deck_uri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("TAG",file.toString());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.


            }
        });
    }

    public void displayData(){
    SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
    String current_deck_name=sp.getString("deck_name","");

    DBHandler myDB=new DBHandler(getContext());
        Cursor cursor= myDB.getDeck(current_deck_name);
        if(cursor.getCount()==0){


        }else{
            cursor.moveToFirst();
            do {

                card_img.add(myDB.getImage(cursor.getBlob(1)));


            }while (cursor.moveToNext());
        }
    }
}