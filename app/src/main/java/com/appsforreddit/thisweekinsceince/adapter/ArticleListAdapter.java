package com.appsforreddit.thisweekinsceince.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appsforreddit.thisweekinsceince.R;
import com.appsforreddit.thisweekinsceince.data.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.deanwild.flowtextview.FlowTextView;

/**
 * Created by Michael on 2/12/2015.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private ArrayList<Article> data;
    private Context context;

    public ArticleListAdapter(ArrayList<Article> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleListAdapter.ViewHolder holder, int position) {
        Article article = data.get(position);
        if (null != article){
            String description = article.getShortDescription();
            if (null != description) {
                holder.tvDescription.setColor(context.getResources().getColor(R.color.twis_blue));
                holder.tvDescription.setText(Html.fromHtml(description));
                holder.tvDescription.setTextSize(context.getResources().getDimension(R.dimen.text_large));

            }
            String imageUrl = article.getImageUrl();
            if (null != imageUrl){
                Picasso.with(context).load(imageUrl).into(holder.ivArticle);
            }

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(Article article) {
        insert(article, data.size());
    }

    public void insert(Article article, int position) {
        data.add(position, article);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(ArrayList<Article> articles){
        this.data = articles;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FlowTextView tvDescription;
        ImageView ivArticle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDescription = (FlowTextView) itemView.findViewById(R.id.tvDescription);
            ivArticle = (ImageView) itemView.findViewById(R.id.ivArticle);
        }
    }
}
