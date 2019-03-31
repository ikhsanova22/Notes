package com.example.nootes;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notes.db";
    public static final String DATABASE_TABLE = "notes";
    public static final String ID = "_id";
    public static final String TEXT = "_text";
    public static final String DATE = "_date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DATABASE_TABLE+"("+
                ID +" integer primary key autoincrement, " +
                TEXT + " text not null, "+DATE +" text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DATABASE_TABLE);
    }

    public void insertNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TEXT, note.getText());
        cv.put(DATE, note.getDate());
        db.insert(DATABASE_TABLE, null, cv);
    }

    public boolean deleteNote(int number){
        SQLiteDatabase db = getWritableDatabase();
        if (number != -1)
            return db.delete(DATABASE_TABLE, ID + "=" + number, null) > 0;
        else return false;

    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String all_notes_query = "select * from "+DATABASE_TABLE;
        Cursor c = db.rawQuery(all_notes_query, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            notes.add(0,new Note(c.getString(c.getColumnIndex(TEXT))));
        }
        return notes;
    }

    public void update(int id, String text,String data){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + DATABASE_TABLE + " SET "+ " ," +
                TEXT + " = '" + text + "'" + " , " + DATE + " = '" + data + "'" + " WHERE " +
                ID  + " = '" + id + "'";
        db.execSQL(query);
    }

}
