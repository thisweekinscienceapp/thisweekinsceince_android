package com.appsforreddit.thisweekinsceince.presenter;

import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;

/**
 * Created by Michael on 1/29/2015.
 */
public interface WeekListPresenter {
    public void onRefresh();
    public void onItemClicked(WeekPost weekPost);
}
