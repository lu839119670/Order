package com.example.order.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "Order.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table menu(category varchar,name varchar,money double,weight varchar,number int,spicy varchar,image int)";
        String sql1 = "create table preview(name varchar,money double,number int,weight varchar,spicy varchar)";
        String sql2 = "create table mtable(tablenum int,pernum int,name varchar,money double,number int,weight varchar,spicy varchar)";
        String sql3 = "create table staff(user varchar,password varchar,name varchar,sex varchar,position varchar,phone varchar)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
