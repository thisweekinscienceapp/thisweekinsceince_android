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

import com.appsforreddit.thisweekinsceince.adapter.ArticleListAdapter;
import com.appsforreddit.thisweekinsceince.data.Article;
import com.appsforreddit.thisweekinsceince.data.repository.ArticleRepositoryImpl;
import com.appsforreddit.thisweekinsceince.interactor.ArticleListInteractorImpl;
import com.appsforreddit.thisweekinsceince.presenter.ArticleListPresenter;
import com.appsforreddit.thisweekinsceince.presenter.ArticleListPresenterImpl;
import com.appsforreddit.thisweekinsceince.view.ArticleListView;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RSSFragment extends Fragment implements ArticleListView, OnMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private ArticleListPresenter presenter;
    private SuperRecyclerView recyclerView;
    private ArticleListAdapter adapter;

    public RSSFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ArticleListPresenterImpl(this, new ArticleListInteractorImpl(new ArticleRepositoryImpl()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rs, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (SuperRecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ArticleListAdapter(new ArrayList<Article>(), getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setupMoreListener(this, 1);
        recyclerView.setRefreshListener(this);
        presenter.onRefresh();
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
    public void showArticles(List<Article> articles) {
        recyclerView.hideProgress();
        recyclerView.hideMoreProgress();
        if (null != adapter) {
            adapter.addAll((ArrayList<Article>) articles);
        }

    }

    @Override
    public void hideArticles() {

    }

    @Override
    public void showError(int errorCode, String reason) {
        recyclerView.hideProgress();
        Toast.makeText(getActivity(), "error loading the science", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMoreAsked(int i, int i2, int i3) {
        presenter.onLoadMore();
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }
}
