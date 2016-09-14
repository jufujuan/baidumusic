package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

/**
 * Created by dllo on 16/9/10.
 * 乐库-->歌单
 */
public class MLSongListFragment extends AbsBaseFragment{

    private RecyclerView mRecyclerView;


    public static MLSongListFragment newInstance() {

        Bundle args = new Bundle();

        MLSongListFragment fragment = new MLSongListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_song_list;
    }

    @Override
    protected void initViews() {
        mRecyclerView=byView(R.id.fra_ml_song_list_recyclerview);
    }

    @Override
    protected void initDatas() {

        //获得网络数据
        getNetDatas();

        mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
    }
    protected void getNetDatas(){
        VolleyInstance.getVolleyInstance().startRequest(Unique.ML_SONG_LIST_url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("歌单"+resultStr);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
