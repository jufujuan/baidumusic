package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/13.
 */
public class MiLoaclFileFragment extends AbsBaseFragment{
    public static MiLoaclFileFragment newInstance() {

        Bundle args = new Bundle();

        MiLoaclFileFragment fragment = new MiLoaclFileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_file;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
