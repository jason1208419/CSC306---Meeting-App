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

import com.example.jason.csc306.Location;
import com.example.jason.csc306.LocationRepo;
import com.example.jason.csc306.MeetingRepo;
import com.example.jason.csc306.R;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listViewLocation);
        MeetingRepo meetingRepo = new MeetingRepo(getActivity());
        LocationRepo locationRepo = new LocationRepo(getActivity());
        ArrayList<HashMap<String, String>> location = locationRepo.getLocationList();
        ArrayList<String> locations = new ArrayList<>();

        for (int i = 0; i < location.size(); i++) {
            int meetingId = Integer.parseInt(location.get(i).get("meetingId"));
            Location location1 = locationRepo.getLocationById(meetingId);
            locations.add(location.get(i).get("name") + "\t\t\tMeeting:\t" +
                    meetingRepo.getMeetingById(meetingId).name
            + "\nlatitude:\t\t\t\t" + location1.latitude + "\nlongitude:\t\t" + location1.longitude
            + "\n" + location1.address);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, locations);
        listView.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Locations");
    }
}
