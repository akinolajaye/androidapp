package com.example.gamepark;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SwitchTurn extends PopupWindow {

    public  SwitchTurn(View contentView, int width, int height, boolean focusable, Player p2,
                       DefenceAdapter defenceAdapter, RecyclerView recyclerView){
        super(contentView,width,height,focusable);

        TextView switch_lbl = contentView.findViewById(R.id.switch_lbl);
        Button switch_btn=contentView.findViewById(R.id.switch_btn);
        switch_lbl.setText("Pass device to next player: "+p2.name);
        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(defenceAdapter);
                dismiss();

            }
        });


    }


}
