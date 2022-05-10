package com.example.gamepark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.MyViewHolder>{

    Context context;
    ArrayList<String> library;
    int card_back_resource;
    Drawable card_back;
    SharedPreferences sp;
    NavController navController;

    LibraryAdapter(Context context, ArrayList<String> library , NavController navController){
        this.context=context;
        this.library=library;
        this.navController=navController;

        card_back_resource=context.getResources().getIdentifier("@drawable/background_gp_splash",
                null, context.getPackageName());

        card_back = context.getResources().getDrawable(card_back_resource);

        sp= context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public LibraryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.library_cells,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.deck_img.setImageDrawable(card_back);
        holder.deck_title.setText(library.get(position));

        holder.deck_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("deck_name",library.get(position));
                editor.commit();
                navController.navigate((R.id.action_viewLibrary_to_deckOfCards));
            }
        });

    }

    @Override
    public int getItemCount() {
        return library.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView deck_img;
        TextView deck_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            deck_img=itemView.findViewById(R.id.deck_img);
            deck_title=itemView.findViewById(R.id.deck_lib_title);


        }
    }
}
