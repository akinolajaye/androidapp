package com.example.gamepark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataTable extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME="decks.db";
    public static final int DB_VERSION=1;
    public  String TABLE_NAME,STAT1_COL,STAT2_COL,STAT3_COL,STAT4_COL,STAT5_COL,STAT6_COL;


    public DataTable(@Nullable Context context,String deck_name,String stat1,String stat2,
                     String stat3,String stat4,String stat5,String stat6) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;

        TABLE_NAME=deck_name;
        STAT1_COL=stat1;
        STAT1_COL=stat2;
        STAT1_COL=stat3;
        STAT1_COL=stat4;
        STAT1_COL=stat5;
        STAT1_COL=stat6;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
