package com.example.pakabuburgerstall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public static String DBNAME = "ordersdb1.db";
    final static int DBVERSION = 1;
    public static String DBTABLE_NAME = "tbl_order";
    public static String date_Table = "date";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME,null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbl_order = "create table tbl_order(order_id integer primary key autoincrement ,total_payment TEXT,payment_method TEXT,date TEXT)";
        sqLiteDatabase.execSQL(tbl_order);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tbl_order");
        onCreate(sqLiteDatabase);
    }
    public Boolean AddOrder(String total_payment,String payment_method)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("total_payment",total_payment);
        contentValues.put("payment_method",payment_method);
        contentValues.put("date",dateFormat.format(date));
        long res = mydb.insert("tbl_order",null,contentValues);
        if(res==-1)
            return false;
        else
            return true;
    }
    public Cursor FetchOrder()
    {
        SQLiteDatabase mydb = getWritableDatabase();
        String qry = "select * from tbl_order";
        Cursor cursor = mydb.rawQuery(qry,null);
        return cursor;
    }

    public Cursor history(String startdate,String enddate) {
        SQLiteDatabase mydb = getWritableDatabase();
        Cursor mCursor = mydb.rawQuery("SELECT * FROM "+ DBTABLE_NAME +
                " WHERE " + date_Table +
                " BETWEEN ?  AND ?", new String[]{startdate, enddate});
        return mCursor;
    }



}
