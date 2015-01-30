package com.appsforreddit.thisweekinsceince.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsforreddit.thisweekinsceince.R;
import com.appsforreddit.thisweekinsceince.data.parse.WeekPost;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Michael on 1/29/2015.
 */

public class WeekListAdapter extends RecyclerView.Adapter<WeekListAdapter.ViewHolder> {

    private ArrayList<WeekPost> data;
    private Context context;

    public WeekListAdapter(ArrayList<WeekPost> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public WeekListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_twis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeekListAdapter.ViewHolder holder, int position) {
        WeekPost weekPost = data.get(position);
        if (null != weekPost){
            String description = weekPost.getDescription();
            if (null != description) {
                holder.tvDescription.setText(description);
            }
            String imageUrl = weekPost.getImageUrl();
            if (null != imageUrl){
                Picasso.with(context).load(imageUrl).into(holder.ivTwis);
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

    public void add(WeekPost weekPost) {
        insert(weekPost, data.size());
    }

    public void insert(WeekPost weekPost, int position) {
        data.add(position, weekPost);
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

    public void addAll(ArrayList<WeekPost> weekPosts){
        this.data = weekPosts;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription;
        ImageView ivTwis;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            ivTwis = (ImageView) itemView.findViewById(R.id.ivTwis);
        }
    }
}