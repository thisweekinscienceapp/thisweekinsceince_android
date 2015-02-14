package com.appsforreddit.thisweekinsceince.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appsforreddit.thisweekinsceince.RSSFragment;
import com.appsforreddit.thisweekinsceince.WeekFragment;

/**
 * Created by Michael on 2/12/2015.
 */
public class TWISFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 2;

    public TWISFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new WeekFragment();
        }

        return new RSSFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "RSS";
            case 1:
                return "TWIS";
        }
        return "";
    }
}
