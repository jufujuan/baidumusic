package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/13.
 */
public class MiLocalSingerFragment extends AbsBaseFragment{
    public static MiLocalSingerFragment newInstance() {

        Bundle args = new Bundle();

        MiLocalSingerFragment fragment = new MiLocalSingerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_singer;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
