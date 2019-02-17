package com.example.jason.csc306;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jason.csc306.fragment.DatePickerFragment;
import com.example.jason.csc306.fragment.TimePickerFragment;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CreateMeetingActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button btnSave, btnCancel;
    Button btnAdd, btnRemove;
    AutoCompleteTextView attendeeInput;
    AutoCompleteTextView locationInput;
    private static final int FLAG_START = 0;
    private static final int FLAG_END = 1;
    private final int PLACE_PICKER_REQUEST = 1;
    private String comparableStartDate = "0";
    private String comparableEndDate = "0";
    private String comparableStartTime = "0";
    private String comparableEndTime = "0";

    private String placeName, address;
    private double latitude, longitude;

    private int flag;
    private int _Meeting_Id = -1;
    private ArrayList<String> attendeesTemp;
    private ArrayList<HashMap<String, String>> attendeesList;
    private String[] attendeesHistory;
    private ArrayList<HashMap<String, String>> locationList;
    private String[] locationsHistory;

    private EditText title, notes;
    private TextView startDate, endDate;
    private TextView startTime, endTime;
    private TextView locationText;
    private TextView attendeesTempText;

    private DatePickerFragment datePicker;
    private TimePickerFragment timePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        this.setTitle("Create Meeting");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AttendeeRepo attendeeRepo = new AttendeeRepo(this);
        attendeesList = attendeeRepo.getAttendeeList();
        int attendeeSize = attendeesList.size();
        attendeesHistory = new String[attendeeSize];
        if (attendeeSize > 0) {
            for (int i = 0; i < attendeeSize; i++){
                attendeesHistory [i] = attendeesList.get(i).get("name");
            }
        }

        LocationRepo locationRepo = new LocationRepo(this);
        locationList = locationRepo.getLocationList();
        int locationSize = locationList.size();
        locationsHistory = new String[locationSize];
        if (locationSize > 0) {
            for (int i = 0; i < locationSize; i++) {
                locationsHistory [i] = locationList.get(i).get("name");
            }
        }

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnRemove = (Button) findViewById(R.id.btn_remove);
        attendeesTempText = (TextView) findViewById(R.id.attendee_list_text);
        attendeesTemp = new ArrayList<>();
        btnAdd.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        attendeeInput = findViewById(R.id.text_add_attendee);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendeesHistory);
        attendeeInput.setAdapter(adapter);
        locationInput = findViewById(R.id.textedit_location_name);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locationsHistory);
        locationInput.setAdapter(adapter1);

        datePicker = new DatePickerFragment();
        timePicker = new TimePickerFragment();

        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);

        locationText = (TextView) findViewById(R.id.textView_location);
        locationText.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        title = (EditText) findViewById(R.id.textedit_name);
        notes = (EditText) findViewById(R.id.editText_notes);

        _Meeting_Id = -1;

        Intent intent = getIntent();
        _Meeting_Id = intent.getIntExtra("meeting_Id", -1);
        MeetingRepo meetingRepo = new MeetingRepo(this);
        Meeting meeting;
        Location location;

        if (_Meeting_Id != -1) {
            meeting = meetingRepo.getMeetingById(_Meeting_Id);
            location = locationRepo.getLocationById(_Meeting_Id);

            for(int j = 0; j < attendeesList.size();j++){
                if (Integer.parseInt(attendeesList.get(j).get("meetingId")) == _Meeting_Id){
                    attendeesTemp.add(attendeesList.get(j).get("name"));
                }
            }

            String title1 = meeting.name;
            String startDate1 = meeting.startDate;
            String startTime1 = meeting.startTime;
            String endDate1 = meeting.endDate;
            String endTime1 = meeting.endTime;
            String locationName = location.name;
            String locationDetail = location.name + ", lad: " +  location.latitude + ", lon: " + location.longitude + ", " + location.address;
            String notes1 = meeting.notes;

            title.setText(title1);
            startDate.setText(startDate1);
            startTime.setText(startTime1);
            endDate.setText(endDate1);
            endTime.setText(endTime1);
            locationInput.setText(locationName);
            locationText.setText(locationDetail);
            notes.setText(notes1);
            comparableStartDate = Long.toString(meeting.comparableStart);
            comparableStartTime = "";
            comparableEndDate = Long.toString(meeting.comparableEnd);
            comparableEndTime = "";
            updateAttendeeTextView ();
        }
    }

    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.startDate) {
            flag = 0;
            datePicker.show(getSupportFragmentManager(), "date picker");
        } else if (id == R.id.endDate) {
            flag = 1;
            datePicker.show(getSupportFragmentManager(), "date picker");
        } else if (id == R.id.startTime) {
            flag = 0;
            timePicker.show(getSupportFragmentManager(), "time picker");
        } else if (id == R.id.endTime) {
            flag = 1;
            timePicker.show(getSupportFragmentManager(), "time picker");
        } else if (id == R.id.textView_location) {
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            final boolean GPS_ENABLED = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (!GPS_ENABLED) {
                Toast toast = Toast.makeText(this, "Please Enable GPS!", Toast.LENGTH_LONG);
                toast.show();
            } else {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.btn_add) {
            String input = attendeeInput.getText().toString();
            if (!input.equalsIgnoreCase("")) {
                attendeesTemp.add(input);
                updateAttendeeTextView();
                attendeeInput.setText("");
            } else {
                Toast toast = Toast.makeText(this, "Please Enter a Name to Add!", Toast.LENGTH_LONG);
                toast.show();
            }

        } else if (id == R.id.btn_remove) {
            String input = attendeeInput.getText().toString();
            if (!input.equalsIgnoreCase("")) {
                boolean removed = attendeesTemp.remove(input);
                if (removed) {
                    updateAttendeeTextView();
                    attendeeInput.setText("");
                } else {
                    attendeeInput.setText("");
                    Toast toast = Toast.makeText(this, "Please Enter a Valid Name To Remove!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        } else if (id == R.id.btnSave) {
            MeetingRepo meetingRepo = new MeetingRepo(this);
            Meeting meeting = new Meeting();
            LocationRepo locationRepo = new LocationRepo(this);
            Location location = new Location();
            AttendeeRepo attendeeRepo = new AttendeeRepo(this);
            Attendee attendee = new Attendee();

            String name1 = title.getText().toString();
            String sDate = startDate.getText().toString();
            String sTime = startTime.getText().toString();
            String eDate = endDate.getText().toString();
            String eTime = endTime.getText().toString();
            String lName = locationInput.getText().toString();
            String lLabel = locationText.getText().toString();
            String note = notes.getText().toString();
            int meetId = searchLocation(lName);
            if (lLabel.equalsIgnoreCase("Click to Pick a location...") && meetId != -1){
                Location location1= locationRepo.getLocationById(meetId);
                placeName = location1.name;
                address = location1.address;
                latitude = location1.latitude;
                longitude = location1.longitude;
                locationText.setText(placeName + ", lat: " + latitude + ", lon: " + longitude + address);
                lLabel = locationText.getText().toString();
            }

            String comparableStartString = comparableStartDate + comparableStartTime;
            long comparableStart = Long.parseLong(comparableStartString);
            String comparableEndString = comparableEndDate + comparableEndTime;
            long comparableEnd = Long.parseLong(comparableEndString);

            if (isFilled(name1, sDate, sTime, eDate, eTime, lName, lLabel) && (comparableEnd > comparableStart)) {
                meeting.name = name1;
                meeting.startDate = sDate;
                meeting.startTime = sTime;
                meeting.endDate = eDate;
                meeting.endTime = eTime;
                meeting.comparableStart = comparableStart;
                meeting.comparableEnd = comparableEnd;
                meeting.notes = note;

                if (_Meeting_Id == -1) {
                    _Meeting_Id = meetingRepo.insert(meeting);
                    location.meetingId = _Meeting_Id;
                    location.name = lName;
                    location.address = address;
                    location.latitude = latitude;
                    location.longitude = longitude;
                    locationRepo.insert(location);
                    for (int i = 0; i < attendeesTemp.size(); i++) {
                        attendee.meetingId = _Meeting_Id;
                        attendee.name = attendeesTemp.get(i);
                        attendeeRepo.insert(attendee);
                    }
                } else {
                    meeting.meetingId = _Meeting_Id;
                    meetingRepo.update(meeting);
                    location.meetingId = _Meeting_Id;
                    location.name = lName;
                    location.address = address;
                    location.latitude = latitude;
                    location.longitude = longitude;
                    locationRepo.update(location);
                    attendeeRepo.delete(_Meeting_Id);
                    for (int i = 0; i < attendeesTemp.size(); i++) {
                        attendee.meetingId = _Meeting_Id;
                        attendee.name = attendeesTemp.get(i);
                        attendeeRepo.insert(attendee);
                    }
                }
                Toast toast = Toast.makeText(this, "Successfully Saved!", Toast.LENGTH_LONG);
                toast.show();
                finish();
            } else if (comparableEnd < comparableStart) {
                Toast toast = Toast.makeText(this, "Stat Date is Lager Than End Date!", Toast.LENGTH_LONG);
                toast.show();
            } else {
                    Toast toast = Toast.makeText(this, "Please Fill in All Except notes!", Toast.LENGTH_LONG);
                    toast.show();
            }
        } else if (id == R.id.btnCancel) {
            finish();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        month+=1;
        String d,m;

        if (dayOfMonth<10){
            d = "0" + dayOfMonth;
        } else {
            d = ""+dayOfMonth;
        }

        if(month<10){
            m = "0" + month;
        } else {
            m = ""+month;
        }

        if (flag == FLAG_START) {
            startDate = (TextView) findViewById(R.id.startDate);
            startDate.setText(currentDateString);

            comparableStartDate= year+m+d;
        } else if (flag == FLAG_END) {
            endDate = (TextView) findViewById(R.id.endDate);
            endDate.setText(currentDateString);

            comparableEndDate= year+m+d;
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String currentTimeString;
        String h, m;
        if (i < 10) {
            h = "0" + i;
        } else {
            h = ""+i;
        }
        if (i1 < 10) {
            m = "0" + i1;
        } else {
            m = ""+i1;
        }
        currentTimeString = h + ":" + m;

        if (flag == FLAG_START) {
            startTime = (TextView) findViewById(R.id.startTime);
            startTime.setText(currentTimeString);

            comparableStartTime = h+m;
        } else if (flag == FLAG_END) {
            endTime = (TextView) findViewById(R.id.endTime);
            endTime.setText(currentTimeString);

            comparableEndTime = h+m;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            displaySelectedPlace(data);
        }
    }

    private void displaySelectedPlace (Intent data) {
        Place placeSelected = PlacePicker.getPlace(this, data);

        placeName = placeSelected.getName().toString();
        address = placeSelected.getAddress().toString();
        latitude = placeSelected.getLatLng().latitude;
        longitude = placeSelected.getLatLng().longitude;
        locationText.setText(placeName + ", lat: " + latitude + ", lon: " + longitude + ", " + address);
        locationInput.setText(placeName);
    }

    private void updateAttendeeTextView () {
        String all = "";
        for (int i = 0; i < attendeesTemp.size(); i++) {
            if (i == 0) {
                all += attendeesTemp.get(i);
            } else {
                all += ", " + attendeesTemp.get(i);
            }
        }
        attendeesTempText.setText(all);
    }

    private boolean isFilled(String name1, String sDate, String sTime, String eDate, String eTime, String lName, String lLabel) {
        final String NULL_DATE = "Click to Pick a Date...";
        final String NULL_TIME = "Click to Pick a Time...";
        final String NULL_LOCATION = "Click to Pick a location...";
        if (!(name1.equalsIgnoreCase("") || sDate.equalsIgnoreCase(NULL_DATE)
                || sTime.equalsIgnoreCase(NULL_TIME) || eDate.equalsIgnoreCase(NULL_DATE)
        || eTime.equalsIgnoreCase(NULL_TIME) || lName.equalsIgnoreCase("")
        || lLabel.equalsIgnoreCase(NULL_LOCATION) || attendeesTemp.size() == 0)) {
            return true;
        }
        return false;
    }

    private int searchLocation (String name) {
        for (int i =0; i < locationList.size(); i++) {
            if(locationList.get(i).containsValue(name)) {
                return Integer.parseInt(locationList.get(i).get("meetingId"));
            }
        }
        return -1;
    }
}
