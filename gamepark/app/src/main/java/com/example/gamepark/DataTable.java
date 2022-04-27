package com.example.gamepark;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataTable extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME="decks.db";
    private static final int DB_VERSION=1;
  ;


    public DataTable(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }




    void add(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("character","tests");
        db.insert("Anime",null,cv);

    }







}
