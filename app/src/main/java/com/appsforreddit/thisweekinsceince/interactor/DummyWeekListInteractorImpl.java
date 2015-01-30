package com.appsforreddit.thisweekinsceince.interactor;

import com.appsforreddit.thisweekinsceince.OnWeekListAvailableListener;
import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 1/29/2015.
 */
public class DummyWeekListInteractorImpl implements WeekListInteractor {
    @Override
    public void loadWeekList(OnWeekListAvailableListener listener) {
        List<WeekPost> weekPostList = new ArrayList<WeekPost>();
        WeekPost weekPost = new WeekPost();
        weekPost.setDescription("some description");
        weekPost.setImageUrl("http://www.unicorns.com/images/indigosea2c.jpg");
        weekPostList.add(weekPost);
        listener.onWeekListAvailable(weekPostList);
    }
}
