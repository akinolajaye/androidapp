package com.example.gamepark;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataTable extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME="decks.db";
    private static final int DB_VERSION=2;
    private   String TABLE_NAME,CHAR_IMG,CHAR_NAME,STAT1_COL
            ,STAT2_COL,STAT3_COL,STAT4_COL,STAT5_COL,STAT6_COL,query;



    public DataTable(@Nullable Context context,String deck_name,String stat1,String stat2,
                     String stat3,String stat4,String stat5,String stat6) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;

        TABLE_NAME=deck_name;
        CHAR_IMG="char_img";
        CHAR_NAME="character";
        STAT1_COL=stat1;
        STAT2_COL=stat2;
        STAT3_COL=stat3;
        STAT4_COL=stat4;
        STAT5_COL=stat5;
        STAT6_COL=stat6;

        query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + "charid" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CHAR_IMG + " BLOB, " +
                        CHAR_NAME + " TEXT, " +
                        STAT1_COL + " TEXT, " +
                        STAT2_COL + " TEXT, " +
                        STAT3_COL + " TEXT, " +
                        STAT4_COL + " TEXT, " +
                        STAT5_COL + " TEXT, " +
                        STAT6_COL + " TEXT);";



    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);

    }

    void add(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHAR_NAME,"tests");
        db.insert(TABLE_NAME,null,cv);

    }




}
