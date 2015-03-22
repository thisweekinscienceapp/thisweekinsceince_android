package com.appsforreddit.thisweekinsceince.presenter;

import com.appsforreddit.thisweekinsceince.R;
import com.appsforreddit.thisweekinsceince.TWISApplication;
import com.appsforreddit.thisweekinsceince.data.NotificationType;
import com.appsforreddit.thisweekinsceince.interactor.NotificationSettingsInteractor;
import com.appsforreddit.thisweekinsceince.view.SettingsView;

/**
 * Created by Michael on 3/21/2015.
 */
public class SettingsPresenterImpl implements SettingsPresenter {

    private SettingsView view;
    private NotificationSettingsInteractor notificationSettingsInteractor;

    public SettingsPresenterImpl(SettingsView view, NotificationSettingsInteractor notificationSettingsInteractor){
        this.view = view;
        this.notificationSettingsInteractor = notificationSettingsInteractor;
    }

    @Override
    public void onResume() {
        boolean twisEnabled = notificationSettingsInteractor.loadNotificationsEnabled(NotificationType.TWIS);
        view.hideError();
        view.showTitle(TWISApplication.getInstance().getString(R.string.notifications_control_title));
        if (twisEnabled) {
            view.showTWISNotifiationsOn();
        }else{
            view.showTWISNotificationsOff();
        }
    }

    @Override
    public void onNotificationsChanged(NotificationType notificationType, final boolean enabled) {
        notificationSettingsInteractor.enableTWISNotifications(new NotificationSettingsInteractor.TWISNotificationsEnabledListener() {
            @Override
            public void onTWISNotificationsEnabled() {
                if (enabled){
                    view.showTWISNotifiationsOn();
                }else{
                    view.showTWISNotificationsOff();
                }
            }

            @Override
            public void onError(int errorCode, String reason) {
                view.hideTWISNotifications();
                view.hideTitle();
                view.showError(errorCode, reason);
            }
        }, enabled);
    }
}
