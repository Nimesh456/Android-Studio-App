package org.example.fyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nimesh on 19/04/2018.
 */

public class ReminderDatabase  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appointments.db";

    private static final String TAG = "ReminderDatabase";


    public static final String TABLE_NAME = "appointments";
    public static final String COLID = "ID";
    public static final String COLdate = "date";
    public static final String COLtime = "time";
    public static final String COLtitle = "title";
    public static final String COLdetails = "details";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" + COLID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLdate + " NUMERIC, " +
                    COLtime + " NUMERIC, " +
                    COLtitle + " TEXT, " +
                    COLdetails + " TEXT " +
                    ")";


    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String date, String time, String title, String details) {//adds data into database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLdate, date);
        contentValues.put(COLtime, time);
        contentValues.put(COLtitle, title);
        contentValues.put(COLdetails, details);

        Log.d(TAG, "addData: Adding " + date + ", " + time + ", " + title + ", " + details + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLdate + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getData1(){ // selects everything from database
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getData2(String title){ //selects everything from table where the title is
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLtitle + " = '" + title + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getitemID(String title){ //returns id associated with name
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT " + COLID + " FROM " + TABLE_NAME +
                " WHERE "+ COLtitle + " = '" + title + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public Cursor getitemID2(String time,String title){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "SELECT " + COLID + " FROM " + TABLE_NAME +
                " WHERE "  + COLtitle + " = '" + title + "'" + " AND " +COLtime + " = '" + time + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public void DeleteData(int id, String title){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COLID + " = '" + id + "'" +
                " AND " + COLtitle + " = '" + title + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + title + " from database.");
        db.execSQL(query);
    }
    public void DeleteAll(String date){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "Delete From " + TABLE_NAME + " WHERE "
                + COLdate + " = '" + date + "'";
        db.execSQL(query);
    }
    //updates database after the edit is done
    public boolean updateData(int id, String time, String title,String details){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues args=new ContentValues();
        args.put(COLtime,time);
        args.put(COLtitle,title);
        args.put(COLdetails,details);
        return
                db.update(TABLE_NAME,args,COLID+"="+id,null)>0;
    }
    public boolean updateDate(int id,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLdate,date);
        return db.update(TABLE_NAME,args,COLID+"="+id,null)>0;
    }
}
