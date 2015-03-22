package com.appsforreddit.thisweekinsceince;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.appsforreddit.thisweekinsceince.data.repository.NotificationRepository;
import com.appsforreddit.thisweekinsceince.data.repository.NotificationRepositoryImpl;
import com.appsforreddit.thisweekinsceince.interactor.NotificationSettingsInteractor;
import com.appsforreddit.thisweekinsceince.interactor.NotificationSettingsInteractorImpl;
import com.appsforreddit.thisweekinsceince.presenter.SettingsPresenter;
import com.appsforreddit.thisweekinsceince.presenter.SettingsPresenterImpl;
import com.appsforreddit.thisweekinsceince.view.SettingsView;


public class SettingsActivity extends ActionBarActivity implements SettingsFragment.SettingsFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.action_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public SettingsPresenter getSettingsPresenter(SettingsView view) {
        NotificationRepository notificationRepository = new NotificationRepositoryImpl(this);
        NotificationSettingsInteractor interactor = new NotificationSettingsInteractorImpl(notificationRepository);
        SettingsPresenter settingsPresenter = new SettingsPresenterImpl(view, interactor);
        return settingsPresenter;
    }
}
