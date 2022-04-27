package com.example.gamepark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private DataTable dbHelper;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException {
        this.dbHelper = new DataTable(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.dbHelper.close();
    }

    /*public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.NAME, name);
        contentValue.put(SQLiteHelper.AGE, desc);
        this.database.insert(SQLiteHelper.TABLE_NAME_STUDENT, null, contentValue);
    }*/


    public Cursor fetch() {
        Cursor cursor = this.database.rawQuery("PRAGMA table_info( Anime )",null );


        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void newDeck(String deck_name,String stat1,String stat2,
                 String stat3,String stat4,String stat5,String stat6){
        String query =
                "CREATE TABLE " + deck_name +
                        " (" + "charid" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "char_img" + " BLOB, " +
                        "character" + " TEXT, " +
                        stat1 + " TEXT, " +
                        stat2 + " TEXT, " +
                        stat3 + " TEXT, " +
                        stat4 + " TEXT, " +
                        stat5 + " TEXT, " +
                        stat6 + " TEXT);";

        this.database.execSQL(query);



    }

    /*public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.NAME, name);
        contentValues.put(SQLiteHelper.AGE, desc);
        return this.database.update(SQLiteHelper.TABLE_NAME_STUDENT, contentValues, "_id = " + _id, null);
    }

    public void delete(long _id) {
        this.database.delete(SQLiteHelper.TABLE_NAME_STUDENT, "_id=" + _id, null);
    }*/
}