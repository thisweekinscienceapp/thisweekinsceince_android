package com.appsforreddit.thisweekinsceince.interactor;

import com.appsforreddit.thisweekinsceince.data.Article;
import com.appsforreddit.thisweekinsceince.data.repository.ArticleRepository;

import java.util.List;

/**
 * Created by Michael on 2/12/2015.
 */
public class ArticleListInteractorImpl implements ArticleListInteractor {

    private ArticleRepository repository;
    public ArticleListInteractorImpl(ArticleRepository repository){
        this.repository = repository;
    }

    @Override
    public void loadArticleList(final ArticleListAvailableListener listener) {
        repository.loadArticleList(new ArticleRepository.ArticleListAvailableListener() {
            @Override
            public void onArticleListAvailable(List<Article> articleList) {
                listener.onArticleListAvailable(articleList);
            }

            @Override
            public void onError(int error, String reason) {

                listener.onError(error, reason);
            }
        });
    }

    @Override
    public void loadArticlesAtPage(final ArticleListPageAvailableListener listener, final int page) {
        repository.loadArticleList(new ArticleRepository.ArticleListAvailableListener() {
            @Override
            public void onArticleListAvailable(List<Article> articleList) {
                listener.onArticleListPageAvailable(articleList, page);
            }

            @Override
            public void onError(int error, String reason) {

                listener.onError(error, reason);
            }
        }, page);
    }
}
