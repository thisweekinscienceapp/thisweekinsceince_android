package com.appsforreddit.thisweekinsceince;

import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;

import java.util.List;

/**
 * Created by Michael on 1/29/2015.
 */
public interface OnWeekListAvailableListener {
    public void onWeekListAvailable(List<WeekPost> weekList);
    public void onError(int errorCode, String reason);
}
