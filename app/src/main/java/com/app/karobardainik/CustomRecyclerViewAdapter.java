package com.app.karobardainik;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by bugatti on 24/11/16.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // view types
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_NEWS = 1;
    public static final int VIEW_TYPE_CARTOON = 2;
    public static final int VIEW_TYPE_MARKET = 3;
    public static final int VIEW_TYPE_VIDEOS = 4;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
