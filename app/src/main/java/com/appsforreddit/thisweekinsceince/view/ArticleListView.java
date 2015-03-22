package com.appsforreddit.thisweekinsceince.view;

import com.appsforreddit.thisweekinsceince.data.Article;

import java.util.List;

/**
 * Created by Michael on 2/12/2015.
 */
public interface ArticleListView {
    public void showArticles(List<Article> articles);
    public void hideArticles();
    public void navigate(Article article);
    public void showError(int errorCode, String reason);
}
