package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/12.
 */
public class MICurrentPlayFragment extends AbsBaseFragment{
    public static MICurrentPlayFragment newInstance() {

        Bundle args = new Bundle();

        MICurrentPlayFragment fragment = new MICurrentPlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_current_play;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
