package com.appsforreddit.thisweekinsceince.interactor;

import com.appsforreddit.thisweekinsceince.data.NotificationType;
import com.appsforreddit.thisweekinsceince.data.repository.NotificationRepository;

/**
 * Created by Michael on 3/21/2015.
 */
public class NotificationSettingsInteractorImpl implements NotificationSettingsInteractor {

    protected NotificationRepository repository;

    public NotificationSettingsInteractorImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void enableTWISNotifications(final TWISNotificationsEnabledListener listener, boolean enabled) {
        repository.enableTWISNotifications(new NotificationRepository.TWISNotitificationsEnabledListener() {
            @Override
            public void onTWISNotificationsEnabled() {
                listener.onTWISNotificationsEnabled();
            }

            @Override
            public void onError(int errorCode, String reason) {
                listener.onError(errorCode, reason);
            }
        }, enabled);
    }

    @Override
    public boolean loadNotificationsEnabled(NotificationType notificationType) {
        return repository.loadNotificationsEnabled(notificationType);
    }
}
