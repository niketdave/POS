package com.example.niket.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by niket on 13-10-2017.
 */

public class HelperforRating extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "result.db";
    // private  static final String DATABASE_NAME = "result";
    private static final String TABLE_RESULT = "Result";
    private static final String KEY_RATING = "rating";

    public HelperforRating(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_RESULTS_TABLE = "CREATE TABLE " + TABLE_RESULT + "(" +KEY_RATING + " REAL)";
        db.execSQL(CREATE_RESULTS_TABLE);
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULT);
        //create tables again
        onCreate(db);
    }

    public boolean addResult(float ratings){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_RATING,ratings);

        long result = db.insert(TABLE_RESULT,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public String getAllData() {
        String rating = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_RESULT, null);
        if (res.moveToFirst()) {
            do {
                 rating = String.valueOf(res.getFloat(0));

            } while (res.moveToNext());
        }
        return rating;
    }

}
