package com.appsforreddit.thisweekinsceince.presenter;

import com.appsforreddit.thisweekinsceince.data.Article;
import com.appsforreddit.thisweekinsceince.interactor.ArticleListInteractor;
import com.appsforreddit.thisweekinsceince.view.ArticleListView;

import java.util.List;

/**
 * Created by Michael on 2/12/2015.
 */
public class ArticleListPresenterImpl implements ArticleListPresenter, ArticleListInteractor.ArticleListAvailableListener, ArticleListInteractor.ArticleListPageAvailableListener {

    private static final int ARTICLES_PER_LOAD = 5;

    private ArticleListInteractor articleListInteractor;
    private ArticleListView view;
    private List<Article> inMemoryArticles;

    public ArticleListPresenterImpl(ArticleListView view, ArticleListInteractor articleListInteractor){
        this.view = view;
        this.articleListInteractor = articleListInteractor;
    }

    @Override
    public void onResume() {
        articleListInteractor.loadArticleList(this);
    }

    @Override
    public void onRefresh() {
        articleListInteractor.loadArticleList(this);
    }

    @Override
    public void onLoadMore() {
        if (null == inMemoryArticles || inMemoryArticles.size() == 0){
            onRefresh();
        }else {
            int size = inMemoryArticles.size();
            int pageIndex = (size / ARTICLES_PER_LOAD) + 2;
            articleListInteractor.loadArticlesAtPage(this, pageIndex);
        }
    }

    @Override
    public void onArticleClick(Article article) {
        view.navigate(article);
    }

    @Override
    public void onArticleListAvailable(List<Article> articleList) {
        view.showArticles(articleList);
        this.inMemoryArticles = articleList;
    }

    @Override
    public void onArticleListPageAvailable(List<Article> articles, int page) {
        int size = inMemoryArticles.size();
        int pageIndex = (size / ARTICLES_PER_LOAD) + 2;
        if (pageIndex == page){
            inMemoryArticles.addAll(articles);
            view.showArticles(inMemoryArticles);
        }
    }

    @Override
    public void onError(int error, String reason) {
        view.showError(error, reason);
    }
}
