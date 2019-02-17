package com.example.jason.csc306;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendeeRepo {
    private DBHelper dbHelper;

    public AttendeeRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Attendee attendee) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Attendee.KEY_meetingID, attendee.meetingId);
        values.put(Attendee.KEY_name, attendee.name);

        long attendee_Id = db.insert(Attendee.TABLE, null, values);
        db.close();
        return (int) attendee_Id;
    }

    public void delete(int meeting_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Attendee.TABLE, Attendee.KEY_meetingID + "= ?", new String[] { String.valueOf(meeting_Id) });
        db.close();
    }

    public void update(Attendee attendee) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Attendee.KEY_meetingID, attendee.meetingId);
        values.put(Attendee.KEY_name, attendee.name);

        db.update(Attendee.TABLE, values, Attendee.KEY_meetingID + "= ?", new String[] { String.valueOf(attendee.meetingId) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAttendeeList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Attendee.KEY_meetingID + ", " +
                Attendee.KEY_name +
                " FROM " + Attendee.TABLE;

        ArrayList<HashMap<String, String>> attendeeList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> attendee = new HashMap<>();
                attendee.put("meetingId", cursor.getString(cursor.getColumnIndex(Attendee.KEY_meetingID)));
                attendee.put("name", cursor.getString(cursor.getColumnIndex(Attendee.KEY_name)));
                attendeeList.add(attendee);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return attendeeList;
    }

    public Attendee getAttendeeById (int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Attendee.KEY_meetingID + ", " +
                Attendee.KEY_name +
                " FROM " + Attendee.TABLE
                + " WHERE " +
                Attendee.KEY_meetingID + "=?";

        int iCount = 0;
        Attendee attendee = new Attendee();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                attendee.meetingId = cursor.getInt(cursor.getColumnIndex(Attendee.KEY_meetingID));
                attendee.name = cursor.getString(cursor.getColumnIndex(Attendee.KEY_name));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return attendee;
    }
}
