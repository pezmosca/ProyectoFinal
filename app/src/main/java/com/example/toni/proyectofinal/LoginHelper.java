package com.example.toni.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Toni on 04/02/2016.
 */
public class LoginHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "usuarios";
    public static final String PLAYERS_TABLE = "jugadores";
    public static final String PLAYERS_TABLE_CREATE = "CREATE TABLE " + PLAYERS_TABLE + "(nick TEXT PRIMARY KEY UNIQUE, password STRING)";


    public LoginHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PLAYERS_TABLE_CREATE);
    }

    public void createPlayer(ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();
    }

    public String getPlayerPassword(String nick) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select password from " + PLAYERS_TABLE  + " where nick =" + "'"+nick+"'", null);
        if(c.moveToFirst()) return c.getString(0);
        else return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
