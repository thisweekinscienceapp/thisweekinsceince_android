package com.appsforreddit.thisweekinsceince.presenter;

import com.appsforreddit.thisweekinsceince.data.NotificationType;

/**
 * Created by Michael on 3/21/2015.
 */
public interface SettingsPresenter {

    public void onResume();

    public void onNotificationsChanged(NotificationType notificationType, boolean enabled);
}
