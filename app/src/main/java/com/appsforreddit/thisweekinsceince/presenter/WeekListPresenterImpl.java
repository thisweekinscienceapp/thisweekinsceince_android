package com.appsforreddit.thisweekinsceince.presenter;

import com.appsforreddit.thisweekinsceince.OnWeekListAvailableListener;
import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;
import com.appsforreddit.thisweekinsceince.interactor.WeekListInteractor;
import com.appsforreddit.thisweekinsceince.view.WeekListView;

import java.util.List;

/**
 * Created by Michael on 1/29/2015.
 */
public class WeekListPresenterImpl implements WeekListPresenter, OnWeekListAvailableListener {

    private WeekListView view;
    private WeekListInteractor interactor;

    public WeekListPresenterImpl(WeekListView view, WeekListInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }
    @Override
    public void onRefresh() {
        interactor.loadWeekList(this);
    }

    @Override
    public void onItemClicked(WeekPost weekPost) {

    }

    @Override
    public void onWeekListAvailable(List<WeekPost> weekList) {
        if (null == weekList || weekList.size() == 0){
            view.hideWeekList();
            view.showError(0, "empty list");
        }else {
            view.showWeekList(weekList);
        }
    }

    @Override
    public void onError(int errorCode, String reason) {
        view.showError(errorCode, reason);
    }
}
