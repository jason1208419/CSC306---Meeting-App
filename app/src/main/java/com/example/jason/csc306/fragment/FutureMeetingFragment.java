package com.example.jason.csc306.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jason.csc306.AttendeeRepo;
import com.example.jason.csc306.Location;
import com.example.jason.csc306.LocationRepo;
import com.example.jason.csc306.Meeting;
import com.example.jason.csc306.MeetingRepo;
import com.example.jason.csc306.R;
import com.example.jason.csc306.ViewDetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class FutureMeetingFragment extends Fragment {
    private ArrayList<Meeting> meetings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future_meeting, container, false);

        MeetingRepo meetingRepo = new MeetingRepo(getActivity());
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
        String current = dateFormat.format(date);
        long now = Long.parseLong(current);
        ArrayList<HashMap<String, String>> meetingList = meetingRepo.getMeetingList();
        meetings = new ArrayList<>();
        for (int i = 0; i < meetingList.size(); i++) {
            Meeting meeting = meetingRepo.getMeetingById(i+1);
            long comparableEnd = meeting.comparableEnd;

            if (comparableEnd > now) {
                meetings.add(meetingRepo.getMeetingById(Integer.parseInt(meetingList.get(i).get("meetingId"))));
            }
        }

        ListView listView = (ListView) view.findViewById(R.id.listViewFuture);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MeetingRepo meetingRepo = new MeetingRepo(getActivity());
                LocationRepo locationRepo = new LocationRepo(getActivity());
                AttendeeRepo attendeeRepo = new AttendeeRepo(getActivity());

                Meeting meeting = meetings.get(position);
                Location location1 = locationRepo.getLocationById(meeting.meetingId);
                ArrayList<HashMap<String, String>> attendee = attendeeRepo.getAttendeeList();
                ArrayList<String> attendees = new ArrayList<>();
                for(int j = 0; j < attendee.size();j++){
                    if (Integer.parseInt(attendee.get(j).get("meetingId")) == meeting.meetingId){
                        attendees.add(attendee.get(j).get("name"));
                    }
                }

                String all = "";
                for (int j = 0; j < attendees.size(); j++) {
                    if (j == 0) {
                        all += attendees.get(j);
                    } else {
                        all += ", " + attendees.get(j);
                    }
                }


                Bundle bundle = new Bundle();
                bundle.putInt("meetingId", meeting.meetingId);
                bundle.putString("title", meeting.name);
                bundle.putString("startDate", meeting.startDate);
                bundle.putString("startTime", meeting.startTime);
                bundle.putString("endDate", meeting.endDate);
                bundle.putString("endTime", meeting.endTime);
                bundle.putString("locationName", location1.name);
                bundle.putString("locationDetail", location1.name + ", lad: " +  location1.latitude + ", lon: " + location1.longitude + ", " + location1.address);
                bundle.putString("notes", meeting.notes);
                bundle.putString("attendees", all);
                bundle.putStringArrayList("attendeesList", attendees);

                Intent intent = new Intent(getActivity(), ViewDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return meetings.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.fragment_meeting_list_content, null);
            TextView title = (TextView) convertView.findViewById(R.id.list_name);
            TextView sDateTime = (TextView) convertView.findViewById(R.id.list_sDateTime);
            TextView eDateTime = (TextView) convertView.findViewById(R.id.list_eDateTime);
            TextView location = (TextView) convertView.findViewById(R.id.list_location);
            LocationRepo locationRepo = new LocationRepo(getActivity());

            Meeting meeting = meetings.get(position);
            Location location1 = locationRepo.getLocationById(meeting.meetingId);

            title.setText(meeting.name);
            sDateTime.setText(meeting.startDate + " " + meeting.startTime);
            eDateTime.setText(meeting.endDate + " " + meeting.endTime);
            location.setText(location1.name);

            return convertView;
        }
    }
}
