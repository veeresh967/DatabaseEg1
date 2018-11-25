package com.veeresh.b37_databaseeg1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by skillgun on 01/09/2017.
 */

//step 2 : create a separate java file
public class MyDatabase {
    //step 5 : create 2 variables
    private MyHelper mh; //for DDL
    private SQLiteDatabase sdb; //for DML
    //step 6 : initialize myhelper object in the constructor
    public MyDatabase(Context c){
        //p1 = context, p2 = database name, p3 = cursorfactory null, p4 = database version num
        mh = new MyHelper(c, "techpalle.db", null, 1);
    }
    //step 7 : open database connection
    public void open(){
        sdb = mh.getWritableDatabase();
    }
    //step 8 : DML OPERATIONS STARTS - INSERT, UPDATE, DELETE, QUERY
    //step 9 : insert method for student table
    public void insertStudent(String name, String sub){
        ContentValues cv = new ContentValues(); //prepare content values
        cv.put("sname", name);
        cv.put("sub", sub);
        sdb.insert("student", null, cv); //pass data to student table
    }
    //step 10 : query method for student table
    public Cursor queryStudent(){
        //in query first parameter is table name, other parameters nulls - will explain later
        Cursor c = sdb.query("student", null, null, null, null, null, null);
        return c;
    }
    //END OF DML OPERATIONS
    //step 11 : close database
    public void close(){
        sdb.close(); //removes the database connection [else memory will be wasted]
    }
    //step 3 : create an inner helper class extending openhelper class
    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //step 4 : create tables
            sqLiteDatabase.execSQL("create table student(_id integer primary key, sname text, sub text);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
