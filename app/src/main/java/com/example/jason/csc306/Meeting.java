package com.example.jason.csc306;

public class Meeting {
    public static final String TABLE = "Meeting";
    public static final String KEY_meetingID = "meetingID";
    public static final String KEY_name = "name";
    public static final String KEY_startDate = "startDate";
    public static final String KEY_endDate = "endDate";
    public static final String KEY_startTime = "startTime";
    public static final String KEY_endTime = "endTime";
    public static final String KEY_comparableStart = "comparableStart";
    public static final String KEY_comparableEnd = "comparableEnd";
    public static final String KEY_notes = "notes";

    public int meetingId;
    public String name;
    public String startDate;
    public String endDate;
    public String startTime;
    public String endTime;
    public long comparableStart;
    public long comparableEnd;
    public String notes;
}
