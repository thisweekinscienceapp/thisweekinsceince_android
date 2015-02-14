package com.appsforreddit.thisweekinsceince.interactor;

import com.appsforreddit.thisweekinsceince.data.Article;

import java.util.List;

/**
 * Created by Michael on 2/12/2015.
 */
public interface ArticleListInteractor {

    interface ArticleListAvailableListener{
        public void onArticleListAvailable(List<Article> articleList);
        public void onError(int error, String reason);
    }

    interface ArticleListPageAvailableListener{
        public void onArticleListPageAvailable(List<Article> articles, int page);
        public void onError(int error, String reason);
    }

    public void loadArticleList(ArticleListAvailableListener listener);
    public void loadArticlesAtPage(ArticleListPageAvailableListener listener, int page);

}
