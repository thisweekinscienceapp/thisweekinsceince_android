package com.appsforreddit.thisweekinsceince;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.appsforreddit.thisweekinsceince.data.NotificationType;
import com.appsforreddit.thisweekinsceince.presenter.SettingsPresenter;
import com.appsforreddit.thisweekinsceince.view.SettingsView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements SettingsView {

    protected TextView tvTitle;
    protected TextView tvError;
    protected TextView tvTWISNotificationsLabel;
    protected SwitchCompat swTWISNotifications;
    protected SettingsPresenter presenter;
    protected SettingsFragmentListener listener;

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvError = (TextView) view.findViewById(R.id.tvError);
        tvTWISNotificationsLabel = (TextView) view.findViewById(R.id.tvTWISNotificationsLabel);
        swTWISNotifications = (SwitchCompat) view.findViewById(R.id.swTWIS);
        swTWISNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onNotificationsChanged(NotificationType.TWIS, isChecked);
            }
        });
        presenter.onResume();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (SettingsFragmentListener) activity;
            this.presenter = listener.getSettingsPresenter(this);
        } catch (ClassCastException e) {
            Log.e("ClassCastException", "Activity must implement SettingsFragmentListener");
        }
    }


    @Override
    public void showTWISNotifiationsOn() {
        swTWISNotifications.setVisibility(View.VISIBLE);
        swTWISNotifications.setEnabled(true);
        swTWISNotifications.setChecked(true);
    }

    @Override
    public void showTWISNotificationsOff() {
        swTWISNotifications.setVisibility(View.VISIBLE);
        swTWISNotifications.setEnabled(true);
        swTWISNotifications.setChecked(false);
    }

    @Override
    public void showTitle(String title) {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    @Override
    public void hideTWISNotifications() {
        swTWISNotifications.setVisibility(View.INVISIBLE);
        tvTWISNotificationsLabel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideTitle() {
        tvTitle.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(int errorCode, String reason) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(reason);
    }

    @Override
    public void hideError() {
        tvError.setVisibility(View.GONE);
    }

    public interface SettingsFragmentListener {
        public SettingsPresenter getSettingsPresenter(SettingsView view);
    }
}
