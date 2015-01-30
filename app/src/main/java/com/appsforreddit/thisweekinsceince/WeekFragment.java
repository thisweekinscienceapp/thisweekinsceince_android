

package com.appsforreddit.thisweekinsceince;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appsforreddit.thisweekinsceince.adapter.WeekListAdapter;
import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;
import com.appsforreddit.thisweekinsceince.interactor.DummyWeekListInteractorImpl;
import com.appsforreddit.thisweekinsceince.interactor.WeekListInteractorImpl;
import com.appsforreddit.thisweekinsceince.presenter.WeekListPresenter;
import com.appsforreddit.thisweekinsceince.presenter.WeekListPresenterImpl;
import com.appsforreddit.thisweekinsceince.view.WeekListView;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RSSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RSSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekFragment extends Fragment implements WeekListView {

    private WeekListPresenter presenter;
    private SuperRecyclerView recyclerView;
    private WeekListAdapter adapter;

    public WeekFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.temp_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (SuperRecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeekListAdapter(new ArrayList<WeekPost>(), getActivity());
        recyclerView.setAdapter(adapter);

        presenter = new WeekListPresenterImpl(this, new WeekListInteractorImpl());
        presenter.onRefresh();
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showWeekList(List<WeekPost> weekPostList) {
        recyclerView.hideProgress();
        if (null != adapter){
            adapter.addAll((ArrayList<WeekPost>) weekPostList);
        }
    }

    @Override
    public void hideWeekList() {

    }

    @Override
    public void showError(int errorCode, String reason) {
        recyclerView.hideProgress();
        Toast.makeText(getActivity(), "error loading the science", Toast.LENGTH_LONG).show();
    }
}
