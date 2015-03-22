package com.appsforreddit.thisweekinsceince.interactor;

import com.appsforreddit.thisweekinsceince.data.NotificationType;

/**
 * Created by Michael on 3/21/2015.
 */
public interface NotificationSettingsInteractor {

    interface TWISNotificationsEnabledListener{
        public void onTWISNotificationsEnabled();
        public void onError(int errorCode, String reason);
    }

    public void enableTWISNotifications(TWISNotificationsEnabledListener listener, boolean enabled);

    public boolean loadNotificationsEnabled(NotificationType notificationType);
}
