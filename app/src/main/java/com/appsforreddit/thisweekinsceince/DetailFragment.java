package com.appsforreddit.thisweekinsceince;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsforreddit.thisweekinsceince.view.DetailView;

import java.util.List;


public class DetailFragment extends Fragment implements DetailView {

    protected ImageView ivArticle;
    protected TextView tvArticle;
    protected TextView tvError;

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivArticle = (ImageView) view.findViewById(R.id.ivArticle);
        tvArticle = (TextView) view.findViewById(R.id.tvArticle);
        tvError = (TextView) view.findViewById(R.id.tvError);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showImage(Bitmap bitmap) {
        ivArticle.setVisibility(View.VISIBLE);
        ivArticle.setImageBitmap(bitmap);
    }

    @Override
    public void showArticle(String articleText) {
        tvArticle.setVisibility(View.VISIBLE);
        tvArticle.setText(articleText);
    }

    @Override
    public void showLinks(List<String> urls) {
        //TODO
    }

    @Override
    public void hideImage() {
        ivArticle.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideArticle() {
        tvArticle.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLinks() {
        //TODO
    }

    @Override
    public void showError(int errorCode, String reason) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(reason);
    }

    @Override
    public void hideError() {
        tvError.setVisibility(View.GONE);
    }
}
