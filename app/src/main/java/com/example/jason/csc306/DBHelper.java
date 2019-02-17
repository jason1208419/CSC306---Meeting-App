package com.example.jason.csc306;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "db";

    public DBHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String CREATE_TABLE_MEETING = "CREATE TABLE " + Meeting.TABLE + "("
                + Meeting.KEY_meetingID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Meeting.KEY_name + " TEXT NOT NULL, "
                + Meeting.KEY_startDate + " TEXT NOT NULL, "
                + Meeting.KEY_endDate + " TEXT NOT NULL, "
                + Meeting.KEY_startTime + " TEXT NOT NULL, "
                + Meeting.KEY_endTime + " TEXT NOT NULL, "
                + Meeting.KEY_comparableStart + " INTEGER NOT NULL, "
                + Meeting.KEY_comparableEnd + " INTEGER NOT NULL, "
                + Meeting.KEY_notes + " TEXT )";

        String CREATE_TABLE_LOCATION = "CREATE TABLE " + Location.TABLE + "("
                + Location.KEY_name + " TEXT NOT NULL, "
                + Location.KEY_address + " TEXT, "
                + Location.KEY_latitude + " REAL NOT NULL, "
                + Location.KEY_longitude + " REAL NOT NULL, "
                + Location.KEY_meetingID + " INTEGER NOT NULL, "
                + " FOREIGN KEY (" + Location.KEY_meetingID + ") REFERENCES " + Meeting.TABLE
                + "(" + Meeting.KEY_meetingID + ") ON DELETE CASCADE, "
                + " PRIMARY KEY (" + Location.KEY_name + ", " + Location.KEY_meetingID + "))";

        String CREATE_TABLE_ATTENDEE = "CREATE TABLE " + Attendee.TABLE + "("
                + Attendee.KEY_name + " TEXT NOT NULL, "
                + Attendee.KEY_meetingID + " INTEGER NOT NULL, "
                + " FOREIGN KEY (" + Attendee.KEY_meetingID + ") REFERENCES " + Meeting.TABLE
                + "(" + Meeting.KEY_meetingID + ") ON DELETE CASCADE, "
                + " PRIMARY KEY (" + Attendee.KEY_name + ", " + Attendee.KEY_meetingID + "))";

        db.execSQL(CREATE_TABLE_MEETING);
        db.execSQL(CREATE_TABLE_LOCATION);
        db.execSQL(CREATE_TABLE_ATTENDEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Meeting.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Location.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Attendee.TABLE);
        onCreate(db);
    }
}
