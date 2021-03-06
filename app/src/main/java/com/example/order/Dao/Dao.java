package com.example.order.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.order.Bean.DishEnum;
import com.example.order.Bean.Manager;
import com.example.order.Bean.Menu;
import com.example.order.Bean.Preview;
import com.example.order.Bean.Table;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    DataBaseHelper dataBaseHelper;

    public Dao(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void insertMenu(String category, String name, double money, String weight, int number, String spicy, int image) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "insert into menu values(?,?,?,?,?,?,?)";
        Object[] objects = new Object[]{category, name, money, weight, number, spicy, image};
        db.execSQL(sql, objects);
        db.close();
    }

    public List<Menu> createmenu(DishEnum dish) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        List<Menu> list = new ArrayList<>();
        Menu menu;
        String sql = "select * from menu where category = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{dish.getName()});
        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            String name = cursor.getString(1);
            double money = cursor.getDouble(2);
            String weight = cursor.getString(3);
            int number = cursor.getInt(4);
            String spicy = cursor.getString(5);
            int image = cursor.getInt(6);
            menu = new Menu(DishEnum.pauseToDishEnum(category), name, money, weight, number, spicy, image);
            list.add(menu);
        }
        cursor.close();
        db.close();
        return list;
    }



    public void createPreview(String name, double money, int number, String weight, String spicy) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "insert into preview values(?,?,?,?,?)";
        Object[] objects = new Object[]{name, money, number, weight, spicy};
        db.execSQL(sql, objects);
        db.close();
    }

    public void deletePreview() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "delete from preview";
        db.execSQL(sql);
        db.close();
    }


    public List<Preview> queryPreview() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "select * from preview";
        List<Preview> list = new ArrayList<>();
        Preview preview;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            double money = cursor.getDouble(1);
            int number = cursor.getInt(2);
            String weight = cursor.getString(3);
            String spicy = cursor.getString(4);
            preview = new Preview(name, money, number, weight, spicy);
            list.add(preview);
        }
        cursor.close();
        db.close();
        return list;
    }

    public int getPreviewNumber(String name) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "select number from preview where name = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{name});
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return 0;
    }

    public boolean checkPreview(String name) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "select * from preview where name = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{name});
        if (cursor.moveToNext()) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean isPreviewNull() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "select * from preview";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public void updatePreviewNumber(int number, String name) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "update preview set number = ? where name = ?";
        db.execSQL(sql, new Object[]{number, name});
        db.close();
    }

    public void deletePreviewItem(String name) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "delete from preview where name =?";
        db.execSQL(sql, new Object[]{name});
        db.close();
    }

    public void change(int image, String name) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "update menu set image = ? where name = ?";
        db.execSQL(sql, new Object[]{image, name});
        db.close();
    }

    public boolean chechTable(int tablenum) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "select * from mtable where tablenum = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{tablenum + ""});
        if (cursor.moveToNext()) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    /**
     * 通过菜品名称获取菜品分类
     *
     * @param name 菜品名称
     * @return 菜品种类
     */
    public DishEnum getCategory(String name) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql = "select category from menu where name = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{name});
        if (cursor.moveToNext()) {
            return DishEnum.pauseToDishEnum(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return null;
    }

    public void createMtable(int tabNum,int perNum,String name,double money,int number,String weight,String spicy){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql="insert into mtable values(?,?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{tabNum,perNum,name,money,number,weight,spicy});
        db.close();
    }

    public Boolean login(String user,String password){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql="select * from staff where user = ? and password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{user, password});
        if (cursor.moveToNext()){
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public List<Table> queryTableByTable(int tabNum){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        List<Table>list=new ArrayList<>();
        String sql="select * from mtable where tablenum = ?";
        Table table;
        Cursor cursor = db.rawQuery(sql, new String[]{tabNum+""});
        while (cursor.moveToNext()){
            int tableNum = cursor.getInt(0);
            int perNum = cursor.getInt(1);
            String name = cursor.getString(2);
            double money = cursor.getDouble(3);
            int number = cursor.getInt(4);
            String weight = cursor.getString(5);
            String spicy = cursor.getString(6);
            table=new Table(tableNum,perNum,name,money,number,weight,spicy);
            list.add(table);
        }
        cursor.close();
        db.close();
        return list;
    }
    public void deleteTable(int tabNum){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql="delete from mtable where tablenum = ?";
        db.execSQL(sql,new Object[]{tabNum});
        db.close();
    }

    public void createManager(String user,String password,String name,String sex,String position,String phone){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql="insert into staff values(?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{user,password,name,sex,position,phone});
        db.close();
    }

    public List<Manager> queryManager(){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        List<Manager>list=new ArrayList<>();
        Manager manager;
        String sql="select * from staff";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String user = cursor.getString(0);
            String password = cursor.getString(1);
            String name = cursor.getString(2);
            String sex = cursor.getString(3);
            String position = cursor.getString(4);
            String phone = cursor.getString(5);
            manager=new Manager(user,password,name,sex,position,phone);
            list.add(manager);
        }
        cursor.close();
        db.close();
        return list;
    }

    public void deleteManager(String user){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String sql="delete from staff where user = ?";
        db.execSQL(sql,new Object[]{user});
        db.close();
    }
}
