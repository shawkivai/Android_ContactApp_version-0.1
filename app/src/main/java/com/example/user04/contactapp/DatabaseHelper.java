package com.example.user04.contactapp;

/**
 * Created by USER04 on 2/19/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER03 on 2/19/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contact_app";
    public static final String TABLE_NAME = "contact_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "email";
    public static final String COMPANY = "company";
    public static final String NO = "number";
    public static final String FAV = "favourite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, company TEXT, number TEXT, favourite INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);
    }

    public boolean insertdata(String name, String email, String company,String no ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PHONE, email);
        contentValues.put(COMPANY, company);
        contentValues.put(NO, no);
        long result=db.insert(TABLE_NAME, null,contentValues );
        if(result== -1){
            return false;

        }
        else
            return true;
    }


    /**
     * return contact as a list
     */


    public List<listitem> getdata(){
//        listitem listitem = new listitem();
        List<listitem> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        listitem listitem = null;
        while (cursor.moveToNext()) {
            listitem= new listitem();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("number"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            listitem.setName(name);
            listitem.setNumber(number);
            listitem.setEmail(email);
            listitem.setCompany(company);
            stringBuffer.append(listitem);
            // stringBuffer.append(dataModel);
            data.add(listitem);
        }



        //

        return data;
    }

    public boolean updateData( String prev_name, String name, String email, String company, String no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();

//        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(PHONE, email);
        contentValues.put(COMPANY, company);
        contentValues.put(NO, no);
        db.update(TABLE_NAME, contentValues, "name= ?",new  String[] {prev_name});
        return true;


    }

    public Integer deletedata(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"name= ?",new String[] {name});

    }






}