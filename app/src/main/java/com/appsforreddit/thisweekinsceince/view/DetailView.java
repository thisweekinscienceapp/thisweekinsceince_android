package com.appsforreddit.thisweekinsceince.view;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Michael on 3/21/2015.
 */
public interface DetailView {

    public void showImage(Bitmap bitmap);

    public void showArticle(String articleText);

    public void showLinks(List<String> urls);

    public void hideImage();

    public void hideArticle();

    public void hideLinks();

    public void showError(int errorCode, String reason);

    public void hideError();
}
