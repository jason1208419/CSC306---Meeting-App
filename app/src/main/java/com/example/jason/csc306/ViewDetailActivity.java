package com.example.jason.csc306;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.csc306.fragment.SettingsFragment;

public class ViewDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView detail_name, detail_startDate, detail_startTime, detail_endDate, detail_endTime,
            detail_location_name, detail_location_view, detail_notes, detail_attendee_list,
            textView, textView2, textView3, textView4, textView6, textView7;
    Button delete, edit;
    Bundle bundle;
    private String title, startDate, startTime, endDate, endTime, locationName, locationDetail, notes, attendees;
    private int _Meeting_Id = -1;
    SettingsFragment setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        delete=(Button)findViewById(R.id.btnDelete);
        edit = (Button) findViewById(R.id.btnEdit);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);

        detail_name=(TextView)findViewById(R.id.detail_name);
        detail_startDate=(TextView)findViewById(R.id.detail_startDate);
        detail_startTime=(TextView)findViewById(R.id.detail_startTime);
        detail_endDate=(TextView)findViewById(R.id.detail_endDate);
        detail_endTime=(TextView)findViewById(R.id.detail_endTime);
        detail_location_name=(TextView)findViewById(R.id.detail_location_name);
        detail_location_view=(TextView)findViewById(R.id.detail_location_view);
        detail_notes=(TextView)findViewById(R.id.detail_notes);
        detail_attendee_list=(TextView)findViewById(R.id.detail_attendee_list);
        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView6=(TextView)findViewById(R.id.textView6);
        textView7=(TextView)findViewById(R.id.textView7);
        detail_name.setTextColor(setting.getColor(this));
        detail_name.setTextSize(setting.getSize(this));
        detail_startDate.setTextColor(setting.getColor(this));
        detail_startDate.setTextSize(setting.getSize(this));
        detail_startTime.setTextColor(setting.getColor(this));
        detail_startTime.setTextSize(setting.getSize(this));
        detail_endDate.setTextColor(setting.getColor(this));
        detail_endDate.setTextSize(setting.getSize(this));
        detail_endTime.setTextColor(setting.getColor(this));
        detail_endTime.setTextSize(setting.getSize(this));
        detail_location_name.setTextColor(setting.getColor(this));
        detail_location_name.setTextSize(setting.getSize(this));
        detail_location_view.setTextColor(setting.getColor(this));
        detail_location_view.setTextSize(setting.getSize(this));
        detail_notes.setTextColor(setting.getColor(this));
        detail_notes.setTextSize(setting.getSize(this));
        detail_attendee_list.setTextColor(setting.getColor(this));
        detail_attendee_list.setTextSize(setting.getSize(this));
        textView.setTextColor(setting.getColor(this));
        textView.setTextSize(setting.getSize(this));
        textView2.setTextColor(setting.getColor(this));
        textView2.setTextSize(setting.getSize(this));
        textView3.setTextColor(setting.getColor(this));
        textView3.setTextSize(setting.getSize(this));
        textView4.setTextColor(setting.getColor(this));
        textView4.setTextSize(setting.getSize(this));
        textView6.setTextColor(setting.getColor(this));
        textView6.setTextSize(setting.getSize(this));
        textView7.setTextColor(setting.getColor(this));
        textView7.setTextSize(setting.getSize(this));

        bundle = getIntent().getExtras();

        _Meeting_Id = bundle.getInt("meetingId");
        title = bundle.getString("title");
        startDate = bundle.getString("startDate");
        startTime = bundle.getString("startTime");
        endDate = bundle.getString("endDate");
        endTime = bundle.getString("endTime");
        locationName = bundle.getString("locationName");
        locationDetail = bundle.getString("locationDetail");
        notes = bundle.getString("notes");
        attendees = bundle.getString("attendees");

        detail_name.setText(title);
        detail_startDate.setText(startDate);
        detail_startTime.setText(startTime);
        detail_endDate.setText(endDate);
        detail_endTime.setText(endTime);
        detail_location_name.setText(locationName);
        detail_location_view.setText(locationDetail);
        detail_notes.setText(notes);
        detail_attendee_list.setText(attendees);
    }
    public void onClick(View view) {
        if (view == findViewById(R.id.btnDelete)) {
            MeetingRepo meetingRepo = new MeetingRepo(this);
            meetingRepo.delete(_Meeting_Id);
            Toast.makeText(this, "Meeting Deleted!", Toast.LENGTH_LONG).show();
            finish();
        } else if (view == findViewById(R.id.btnEdit)) {
            Intent intent = new Intent(this, CreateMeetingActivity.class);
            intent.putExtra("meeting_Id", _Meeting_Id);
            startActivity(intent);
        }
    }
}
