package com.appsforreddit.thisweekinsceince.presenter;

import com.appsforreddit.thisweekinsceince.data.Article;

/**
 * Created by Michael on 2/12/2015.
 */
public interface ArticleListPresenter {
    public void onResume();
    public void onRefresh();
    public void onLoadMore();
    public void onArticleClick(Article article);
}
