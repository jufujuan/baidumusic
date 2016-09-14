package com.jfjmusic.dllo.baidumusic.controller.fragment.mine;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/13.
 * "我的"-->"本地音乐"-->"歌曲"
 */
public class MiLocalSongFragment extends AbsBaseFragment {
    public static MiLocalSongFragment newInstance() {

        Bundle args = new Bundle();

        MiLocalSongFragment fragment = new MiLocalSongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_song;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
