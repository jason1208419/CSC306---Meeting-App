package com.example.jason.csc306.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.jason.csc306.fragment.AllMeetingFragment;
import com.example.jason.csc306.fragment.ExpiredMeetingFragment;
import com.example.jason.csc306.fragment.FutureMeetingFragment;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter {
    private static final int FRAGMENT_COUNT = 3;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FutureMeetingFragment();
            case 1:
                return new ExpiredMeetingFragment();
            case 2:
                return new AllMeetingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Future";
            case 1:
                return "Expired";
            case 2:
                return "All";
        }
        return null;
    }
}
