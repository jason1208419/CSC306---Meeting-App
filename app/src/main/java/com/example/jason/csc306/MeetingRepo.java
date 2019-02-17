package com.example.jason.csc306;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MeetingRepo {
    private DBHelper dbHelper;

    public MeetingRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Meeting meeting) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Meeting.KEY_name, meeting.name);
        values.put(Meeting.KEY_startDate, meeting.startDate);
        values.put(Meeting.KEY_endDate, meeting.endDate);
        values.put(Meeting.KEY_startTime, meeting.startTime);
        values.put(Meeting.KEY_endTime, meeting.endTime);
        values.put(Meeting.KEY_comparableStart, meeting.comparableStart);
        values.put(Meeting.KEY_comparableEnd, meeting.comparableEnd);
        values.put(Meeting.KEY_notes, meeting.notes);

        long meeting_Id = db.insert(Meeting.TABLE, null, values);
        db.close();
        return (int) meeting_Id;
    }

    public void delete(int meeting_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Meeting.TABLE, Meeting.KEY_meetingID + "= ?", new String[] { String.valueOf(meeting_Id) });
        db.close();
    }

    public void update(Meeting meeting) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Meeting.KEY_name, meeting.name);
        values.put(Meeting.KEY_startDate, meeting.startDate);
        values.put(Meeting.KEY_endDate, meeting.endDate);
        values.put(Meeting.KEY_startTime, meeting.startTime);
        values.put(Meeting.KEY_endTime, meeting.endTime);
        values.put(Meeting.KEY_comparableStart, meeting.comparableStart);
        values.put(Meeting.KEY_comparableEnd, meeting.comparableEnd);
        values.put(Meeting.KEY_notes, meeting.notes);

        db.update(Meeting.TABLE, values, Meeting.KEY_meetingID + "= ?", new String[] { String.valueOf(meeting.meetingId) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getMeetingList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Meeting.KEY_meetingID + ", " +
                Meeting.KEY_name + ", " +
                Meeting.KEY_startDate + ", " +
                Meeting.KEY_endDate + ", " +
                Meeting.KEY_startTime + ", " +
                Meeting.KEY_endTime + ", " +
                Meeting.KEY_comparableStart + ", " +
                Meeting.KEY_comparableEnd + ", " +
                Meeting.KEY_notes +
                " FROM " + Meeting.TABLE;

        ArrayList<HashMap<String, String>> meetingList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> meeting = new HashMap<>();
                meeting.put("meetingId", cursor.getString(cursor.getColumnIndex(Meeting.KEY_meetingID)));
                meeting.put("name", cursor.getString(cursor.getColumnIndex(Meeting.KEY_name)));
                meetingList.add(meeting);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return meetingList;
    }

    public Meeting getMeetingById (int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Meeting.KEY_meetingID + ", " +
                Meeting.KEY_name + ", " +
                Meeting.KEY_startDate + ", " +
                Meeting.KEY_endDate + ", " +
                Meeting.KEY_startTime + ", " +
                Meeting.KEY_endTime + ", " +
                Meeting.KEY_comparableStart + ", " +
                Meeting.KEY_comparableEnd + ", " +
                Meeting.KEY_notes +
                " FROM " + Meeting.TABLE
                + " WHERE " +
                Meeting.KEY_meetingID + "=?";

        int iCount = 0;
        Meeting meeting = new Meeting();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                meeting.meetingId = cursor.getInt(cursor.getColumnIndex(Meeting.KEY_meetingID));
                meeting.name = cursor.getString(cursor.getColumnIndex(Meeting.KEY_name));
                meeting.startDate = cursor.getString(cursor.getColumnIndex(Meeting.KEY_startDate));
                meeting.endDate = cursor.getString(cursor.getColumnIndex(Meeting.KEY_endDate));
                meeting.startTime = cursor.getString(cursor.getColumnIndex(Meeting.KEY_startTime));
                meeting.endTime = cursor.getString(cursor.getColumnIndex(Meeting.KEY_endTime));
                meeting.comparableStart = cursor.getLong(cursor.getColumnIndex(Meeting.KEY_comparableStart));
                meeting.comparableEnd = cursor.getLong(cursor.getColumnIndex(Meeting.KEY_comparableEnd));
                meeting.notes = cursor.getString(cursor.getColumnIndex(Meeting.KEY_notes));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return meeting;
    }
}
