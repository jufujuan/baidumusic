package com.jfjmusic.dllo.baidumusic.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/9/14.
 * 乐库-->歌单----的recyclerview的Adapter
 */
public class MLSongListRecyclerAdapter extends RecyclerView.Adapter<MLSongListRecyclerAdapter.myHolder>{

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(myHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class myHolder extends RecyclerView.ViewHolder {

        public myHolder(View itemView) {
            super(itemView);
        }
    }
}
