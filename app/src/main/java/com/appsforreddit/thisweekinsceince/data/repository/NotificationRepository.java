package com.appsforreddit.thisweekinsceince.data.repository;

import com.appsforreddit.thisweekinsceince.data.NotificationType;

/**
 * Created by Michael on 3/21/2015.
 */
public interface NotificationRepository {

    interface TWISNotitificationsEnabledListener{
        public void onTWISNotificationsEnabled();
        public void onError(int errorCode, String reason);
    }

    public void enableTWISNotifications(TWISNotitificationsEnabledListener listener, boolean enabled);
    public boolean loadNotificationsEnabled(NotificationType type);
}
