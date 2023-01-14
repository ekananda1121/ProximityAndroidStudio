package com.example.proximitysensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class Database extends SQLiteOpenHelper{

    public static final String DB_NAME = "db_sensor.db";
    public static final String TB_NAME = "tb_coba";
    public static final String COL_2 = "data";

    public Database(Context context){super(context,DB_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TB_NAME +"(" + COL_2 + "int not null);";
        sqLiteDatabase.execSQL(sql);
    }

    public boolean addData(int data) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Database.COL_2,data);

        long sid = db.insert(Database.TB_NAME, null, values);

        if (sid>0)
            return true;
        else
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
