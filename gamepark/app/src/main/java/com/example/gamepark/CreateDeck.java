package com.example.gamepark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateDeck extends AppCompatActivity {
    String name_of_deck,stat1_val,stat2_val,stat3_val,stat4_val,stat5_val,stat6_val;
    EditText deck_name,stat1,stat2,stat3,stat4,stat5,stat6;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);
        deck_name=(EditText) findViewById(R.id.deck_name);
        stat1=(EditText) findViewById(R.id.stat_1);
        stat2=(EditText) findViewById(R.id.stat_2);
        stat3=(EditText) findViewById(R.id.stat_3);
        stat4=(EditText) findViewById(R.id.stat_4);
        stat5=(EditText) findViewById(R.id.stat_5);
        stat6=(EditText) findViewById(R.id.stat_6);

        submit_btn= (Button) findViewById(R.id.submit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_of_deck=deck_name.getText().toString();
                stat1_val=stat1.getText().toString();
                stat2_val=stat2.getText().toString();
                stat3_val=stat3.getText().toString();
                stat4_val=stat4.getText().toString();
                stat5_val=stat5.getText().toString();
                stat6_val=stat6.getText().toString();


                DataTable myDB= new DataTable(CreateDeck.this,name_of_deck,stat1_val,stat2_val,
                        stat3_val,stat4_val,stat5_val,stat6_val);

                myDB.add();


            }

        });



    }


}