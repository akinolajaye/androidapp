package com.example.gamepark;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ViewCard extends Fragment {

    private String frag_char_name    //stores the name of the recieved variables from the bundle
            ,frag_stat1_lbl,frag_stat1
            ,frag_stat2_lbl,frag_stat2
            ,frag_stat3_lbl,frag_stat3
            ,frag_stat4_lbl,frag_stat4
            ,frag_stat5_lbl,frag_stat5
            ,frag_stat6_lbl,frag_stat6
            ,frag_char_img

            ;
    private byte[] char_icon;
    public ViewCard() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getParentFragmentManager().setFragmentResultListener("card_info", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                frag_char_img=result.getString("char_icon");//gets all the values from the bundle
                frag_char_name=result.getString("char_name");
                frag_stat1_lbl=result.getString("stat1_lbl");
                frag_stat1=result.getString("stat1");
                frag_stat2_lbl=result.getString("stat2_lbl");
                frag_stat2=result.getString("stat2");
                frag_stat3_lbl=result.getString("stat3_lbl");
                frag_stat3=result.getString("stat3");
                frag_stat4_lbl=result.getString("stat4_lbl");
                frag_stat4=result.getString("stat4");
                frag_stat5_lbl=result.getString("stat5_lbl");
                frag_stat5=result.getString("stat5");
                frag_stat6_lbl=result.getString("stat6_lbl");
                frag_stat6=result.getString("stat6");
            }
        });

        return inflater.inflate(R.layout.fragment_view_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button view_button= (Button) view.findViewById(R.id.click);
        Button add_button=(Button) view.findViewById(R.id.add_card);
        SharedPreferences sp=getContext().getApplicationContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String current_deck_name=sp.getString("deck_name","");


        //gets all the xml text view objects that will store the stats
        final RelativeLayout relativeLayout= view.findViewById(R.id.rlayout);

        TextView card_name=(TextView) view.findViewById(R.id.character_name);
        TextView stat1_lbl=(TextView) view.findViewById(R.id.card_stat1_lbl);
        TextView stat1=(TextView) view.findViewById(R.id.card_stat1);
        TextView stat2_lbl=(TextView) view.findViewById(R.id.card_stat2_lbl);
        TextView stat2=(TextView) view.findViewById(R.id.card_stat2);
        TextView stat3_lbl=(TextView) view.findViewById(R.id.card_stat3_lbl);
        TextView stat3=(TextView) view.findViewById(R.id.card_stat3);
        TextView stat4_lbl=(TextView) view.findViewById(R.id.card_stat4_lbl);
        TextView stat4=(TextView) view.findViewById(R.id.card_stat4);
        TextView stat5_lbl=(TextView) view.findViewById(R.id.card_stat5_lbl);
        TextView stat5=(TextView) view.findViewById(R.id.card_stat5);
        TextView stat6_lbl=(TextView) view.findViewById(R.id.card_stat6_lbl);
        TextView stat6=(TextView) view.findViewById(R.id.card_stat6);
        ImageView card_img = (ImageView) view.findViewById(R.id.card_icon);




        //sets the text on the xml to the values from the bundle
        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card_img.setImageURI(Uri.parse(frag_char_img));
                card_name.setText(frag_char_name);
                stat1_lbl.setText(frag_stat1_lbl);
                stat1.setText(frag_stat1);
                stat2_lbl.setText(frag_stat2_lbl);
                stat2.setText(frag_stat2);
                stat3_lbl.setText(frag_stat3_lbl);
                stat3.setText(frag_stat3);
                stat4_lbl.setText(frag_stat4_lbl);
                stat4.setText(frag_stat4);
                stat5_lbl.setText(frag_stat5_lbl);
                stat5.setText(frag_stat5);
                stat6_lbl.setText(frag_stat6_lbl);
                stat6.setText(frag_stat6);


            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            //button to add card to database
            @Override
            public void onClick(View view) {

                Bitmap bitmap= Bitmap.createBitmap(relativeLayout.getWidth(),relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);//create bitmap object
                Canvas canvas = new Canvas(bitmap);//create canvas obejct using bitmap
                relativeLayout.draw(canvas);//draw to relative layout to store the xml layout as an image
                char_icon=getBytes(bitmap); //convert bitmap image to bytes in order to be stored
                SaveImage(bitmap);//save image on phone

                DBHandler myDB = new DBHandler (getContext());//create db handler object

                myDB.addCardToDeck(current_deck_name, //add object to the card deck
                        "char_img",char_icon,
                        "character",frag_char_name,
                        frag_stat1_lbl,frag_stat1,
                        frag_stat2_lbl,frag_stat2,
                        frag_stat3_lbl,frag_stat3,
                        frag_stat4_lbl,frag_stat4,
                        frag_stat5_lbl,frag_stat5,
                        frag_stat6_lbl,frag_stat6
                        );
            }
        });


    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,  0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Pictures");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}