package com.jfjmusic.dllo.baidumusic.controller.fragment.play;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/19.
 */
public class MiddlePlayFragment extends AbsBaseFragment{

    public static MiddlePlayFragment newInstance() {

        Bundle args = new Bundle();

        MiddlePlayFragment fragment = new MiddlePlayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_play_middle;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
