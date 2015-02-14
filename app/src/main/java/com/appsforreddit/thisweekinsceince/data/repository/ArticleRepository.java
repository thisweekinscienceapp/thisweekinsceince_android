package com.appsforreddit.thisweekinsceince.data.repository;

import com.appsforreddit.thisweekinsceince.data.Article;

import java.util.List;

/**
 * Created by Michael on 2/12/2015.
 */
public interface ArticleRepository {

    interface ArticleListAvailableListener{
        public void onArticleListAvailable(List<Article> articleList);
        public void onError(int error, String reason);
    }

    public void loadArticleList(ArticleListAvailableListener listener);
    public void loadArticleList(ArticleListAvailableListener listener, int page);
}
