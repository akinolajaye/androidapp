package com.example.gamepark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ViewCard extends Fragment {



    public ViewCard() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button= (Button) view.findViewById(R.id.click);
        final RelativeLayout relativeLayout= view.findViewById(R.id.rlayout);
        ImageView imageView=view.findViewById(R.id.img);
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

        int img_res = getResources().getIdentifier("@drawable/sas",null, getContext().getPackageName());
        ImageView card_img = (ImageView) view.findViewById(R.id.card_icon);
        Drawable res=getResources().getDrawable(img_res);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                card_img.setImageDrawable(res);


                card_name.setText("Sasuke");
                stat1_lbl.setText("Chakra");
                stat1.setText("85");
                stat2_lbl.setText("Speed");
                stat2.setText("99");
                stat3_lbl.setText("Intellect");
                stat3.setText("99");
                stat4_lbl.setText("Plot");
                stat4.setText("95");
                stat5_lbl.setText("Skill");
                stat5.setText("100");
                stat6_lbl.setText("Strength");
                stat6.setText("80");


                Bitmap bitmap= Bitmap.createBitmap(relativeLayout.getWidth(),relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                relativeLayout.draw(canvas);
                //imageView.setImageBitmap(bitmap);
                SaveImage(bitmap);



            }
        });




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