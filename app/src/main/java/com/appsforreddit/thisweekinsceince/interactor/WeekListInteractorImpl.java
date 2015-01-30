package com.appsforreddit.thisweekinsceince.interactor;

import com.appsforreddit.thisweekinsceince.OnWeekListAvailableListener;
import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 1/29/2015.
 */
public class WeekListInteractorImpl implements WeekListInteractor {


    @Override
    public void loadWeekList(final OnWeekListAvailableListener listener) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    ArrayList<WeekPost> weekPostList = new ArrayList<WeekPost>();
                    for (ParseObject parseObject: parseObjects){
                        WeekPost weekPost = new WeekPost(parseObject);
                        weekPostList.add(weekPost);
                    }
                    listener.onWeekListAvailable(weekPostList);

                } else {
                    listener.onError(0, "parse load failed");
                }
            }
        });
    }
}
