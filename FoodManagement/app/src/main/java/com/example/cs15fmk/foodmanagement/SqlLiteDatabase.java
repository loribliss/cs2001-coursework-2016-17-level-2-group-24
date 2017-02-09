package com.example.cs15fmk.foodmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqlLiteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Customer.db";
    public static final String TABLE_NAME = "Customer_table";

    public static final String COL1 = "USER_NAME";
    public static final String COL2 = "EMAIL_ADDRESS";
    public static final String COL3 = "PASSWORD";

    public static final int DATABASE_VERSION = 1;
    public static final String TAG = "MyActivity";

    public SqlLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Created database ");
    }
    //ERRROR HERE
    public void onCreate(SQLiteDatabase db) {
        String create_table ="CREATE TABLE " + TABLE_NAME
                + "("
                + COL1 + "TEXT ,"
                + COL2 + "TEXT,"
                + COL3 + "TEXT" +
                ")";
        db.execSQL(create_table);
        Log.d(TAG, "Created database ");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, email);
        contentValues.put(COL3, password);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
}




