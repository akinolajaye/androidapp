package com.example.gamepark;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME="decks.db";
    private static final int DB_VERSION=2;

    public String x=DB_NAME;

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }



    public void newDeck(String deck_name,String stat1,String stat2,
                        String stat3,String stat4,String stat5,String stat6){

        String char_img="char_img";
        String character="character";
        String query =
                "CREATE TABLE " + deck_name +
                        " (" + "charid" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        char_img + " BLOB, " +
                        character + " TEXT, " +
                        stat1 + " TEXT, " +
                        stat2 + " TEXT, " +
                        stat3 + " TEXT, " +
                        stat4 + " TEXT, " +
                        stat5 + " TEXT, " +
                        stat6 + " TEXT)";

        final SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(query);
        db.close();



    }


    public Cursor getDeckStats(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =db.rawQuery("PRAGMA table_info( Anime )",null );

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }
}
