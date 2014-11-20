package com.fuseini.instant.weatherapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fuseini.instant.weatherapp.TodayFragment;
import com.fuseini.instant.weatherapp.TomorrowFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    // Constructor
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Today View Fragment activity
                return new TodayFragment();
            case 1:
                // Tomorrow View Fragment activity
                return new TomorrowFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // Tabs item count = number of tabs
        return 2;
    }


}
