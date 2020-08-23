package com.example.niket.foodorder;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by niket on 08-10-2017.
 */

public class DatabaseHepler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "order.db";
    public static final String TABLE_NAME = "order_details";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PRISE";
    public static final String COL_4 = "QUANTITY";
    public static final String COL_5 = "TOTAL";

    public DatabaseHepler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER,NAME TEXT,PRISE INTEGER,QUANTITY INTEGER,TOTAL INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name,String PRISE,String QUANTITY,String TOTAL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,PRISE);
        contentValues.put(COL_4,QUANTITY);
        contentValues.put(COL_5,TOTAL);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id=? ",new String[] {id});

    }
    public void deleteDataall () {
        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("delete from " + TABLE_NAME);
    }
    public boolean updateData(String id,String name,String PRISE,String QUANTITY,String TOTAL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,PRISE);
        contentValues.put(COL_4,QUANTITY);
        contentValues.put(COL_5,TOTAL);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
            return true;
    }

}
