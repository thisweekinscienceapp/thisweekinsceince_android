package com.appsforreddit.thisweekinsceince.data.repository;

import com.appsforreddit.thisweekinsceince.data.DownloadWebPageTask;

/**
 * Created by Michael on 2/12/2015.
 */
public class ArticleRepositoryImpl implements ArticleRepository {
    @Override
    public void loadArticleList(ArticleListAvailableListener listener) {
        loadArticleList(listener, 1);
    }

    @Override
    public void loadArticleList(ArticleListAvailableListener listener, int page) {
        DownloadWebPageTask downloadWebPageTask = new DownloadWebPageTask(listener);
        downloadWebPageTask.execute(new String[]{"http://www.futurism.co/page/" + page});
    }
}
