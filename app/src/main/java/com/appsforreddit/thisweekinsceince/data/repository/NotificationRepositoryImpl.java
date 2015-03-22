package com.appsforreddit.thisweekinsceince.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.appsforreddit.thisweekinsceince.data.NotificationType;

/**
 * Created by Michael on 3/21/2015.
 */
public class NotificationRepositoryImpl implements NotificationRepository {

    private static final String PREF_KEY = "notifications";
    private static final String TWIS_KEY = "TWIS";
    protected SharedPreferences sharedPreferences;

    public NotificationRepositoryImpl(Context context){
        sharedPreferences = context.getSharedPreferences(
                PREF_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void enableTWISNotifications(TWISNotitificationsEnabledListener listener, boolean enabled) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(TWIS_KEY, enabled);
        editor.commit();
    }


    @Override
    public boolean loadNotificationsEnabled(NotificationType type) {
        String key = null;
        switch (type){
            case TWIS:
                key = TWIS_KEY;
        }
        return sharedPreferences.getBoolean(key, false);
    }
}
