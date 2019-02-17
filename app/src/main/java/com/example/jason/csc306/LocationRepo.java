package com.example.jason.csc306;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationRepo {
    private DBHelper dbHelper;

    public LocationRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Location location) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Location.KEY_meetingID, location.meetingId);
        values.put(Location.KEY_name, location.name);
        values.put(Location.KEY_address, location.address);
        values.put(Location.KEY_latitude, location.latitude);
        values.put(Location.KEY_longitude, location.longitude);

        long location_Id = db.insert(Location.TABLE, null, values);
        db.close();
        return (int) location_Id;
    }

    public void delete(int meeting_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Location.TABLE, Location.KEY_meetingID + "= ?", new String[] { String.valueOf(meeting_Id) });
        db.close();
    }

    public void update(Location location) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Location.KEY_meetingID, location.meetingId);
        values.put(Location.KEY_name, location.name);
        values.put(Location.KEY_address, location.address);
        values.put(Location.KEY_latitude, location.latitude);
        values.put(Location.KEY_longitude, location.longitude);

        db.update(Location.TABLE, values, Location.KEY_meetingID + "= ?", new String[] { String.valueOf(location.meetingId) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getLocationList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Location.KEY_meetingID + ", " +
                Location.KEY_name + ", " +
                Location.KEY_address + ", " +
                Location.KEY_latitude + ", " +
                Location.KEY_longitude +
                " FROM " + Location.TABLE;

        ArrayList<HashMap<String, String>> locationList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> location = new HashMap<>();
                location.put("meetingId", cursor.getString(cursor.getColumnIndex(Location.KEY_meetingID)));
                location.put("name", cursor.getString(cursor.getColumnIndex(Location.KEY_name)));
                locationList.add(location);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return locationList;
    }

    public Location getLocationById (int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Location.KEY_meetingID + ", " +
                Location.KEY_name + ", " +
                Location.KEY_address + ", " +
                Location.KEY_latitude + ", " +
                Location.KEY_longitude +
                " FROM " + Location.TABLE
                + " WHERE " +
                Location.KEY_meetingID + "=?";

        int iCount = 0;
        Location location = new Location();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                location.meetingId = cursor.getInt(cursor.getColumnIndex(Location.KEY_meetingID));
                location.name = cursor.getString(cursor.getColumnIndex(Location.KEY_name));
                location.address = cursor.getString(cursor.getColumnIndex(Location.KEY_address));
                location.latitude = cursor.getDouble(cursor.getColumnIndex(Location.KEY_latitude));
                location.longitude = cursor.getDouble(cursor.getColumnIndex(Location.KEY_longitude));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return location;
    }
}
