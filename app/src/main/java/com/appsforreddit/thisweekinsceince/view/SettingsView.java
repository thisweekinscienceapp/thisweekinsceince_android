package com.appsforreddit.thisweekinsceince.view;

/**
 * Created by Michael on 3/21/2015.
 */
public interface SettingsView {

    public void showTWISNotifiationsOn();
    public void showTWISNotificationsOff();
    public void showTitle(String title);
    public void hideTWISNotifications();
    public void hideTitle();
    public void showError(int errorCode, String reason);
    public void hideError();
}
