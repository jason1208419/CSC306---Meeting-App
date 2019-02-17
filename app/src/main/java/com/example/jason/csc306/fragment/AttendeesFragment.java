package com.example.jason.csc306.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jason.csc306.AttendeeRepo;
import com.example.jason.csc306.MeetingRepo;
import com.example.jason.csc306.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendeesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendees, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listViewAttendee);

        MeetingRepo meetingRepo = new MeetingRepo(getActivity());
        AttendeeRepo attendeeRepo = new AttendeeRepo(getActivity());
        ArrayList<HashMap<String, String>> attendee = attendeeRepo.getAttendeeList();
        ArrayList<String> attendees = new ArrayList<>();

        for (int i = 0; i < attendee.size(); i++) {
            attendees.add("Attendee:\t\t" + attendee.get(i).get("name") + "\nMeeting:\t\t" + meetingRepo.getMeetingById(Integer.parseInt(attendee.get(i).get("meetingId"))).name);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, attendees);
        listView.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Attendees");
    }
}
