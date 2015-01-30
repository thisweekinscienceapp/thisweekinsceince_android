package com.appsforreddit.thisweekinsceince;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Michael on 1/29/2015.
 */
public class TWISApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String key1 = getResources().getString(R.string.parse_key1);
        String key2 = getResources().getString(R.string.parse_key2);
        Parse.initialize(this, key1, key2);
    }


}
