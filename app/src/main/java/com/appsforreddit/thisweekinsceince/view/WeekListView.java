package com.appsforreddit.thisweekinsceince.view;

import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;

import java.util.List;

/**
 * Created by Michael on 1/29/2015.
 */
public interface WeekListView {
    public void showWeekList(List<WeekPost> weekPostList);
    public void hideWeekList();
    public void showError(int errorCode, String reason);
}
