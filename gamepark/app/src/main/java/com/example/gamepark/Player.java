package com.example.gamepark;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Player {
    public String name;
    public ArrayList<Card> Deck =new ArrayList<>();
    public Player(String name){

        this.name=name;
    }



    public void addCardToDeck(Bitmap char_card,String char_name,int stat1,int stat2,int stat3,int stat4,int stat5,int stat6){

        Card card=new Card(char_card,char_name,stat1,stat2,stat3,stat4,stat5,stat6);
        Deck.add(card);



    }



}

class Card{

    public Bitmap char_card;
    public String char_name;
    public int stat1,stat2,stat3,stat4,stat5,stat6;

    Card(Bitmap char_card,String char_name,int stat1,int stat2,int stat3,int stat4,int stat5,int stat6){

        this.char_card=char_card;
        this.char_name=char_name;
        this.stat1=stat1;
        this.stat2=stat2;
        this.stat3=stat3;
        this.stat4=stat4;
        this.stat5=stat5;
        this.stat6=stat6;



    }
}
