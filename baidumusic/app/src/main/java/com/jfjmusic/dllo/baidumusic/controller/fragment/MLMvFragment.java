package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/10.
 */
public class MLMvFragment extends AbsBaseFragment{
    public static MLMvFragment newInstance() {

        Bundle args = new Bundle();

        MLMvFragment fragment = new MLMvFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_mv;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
