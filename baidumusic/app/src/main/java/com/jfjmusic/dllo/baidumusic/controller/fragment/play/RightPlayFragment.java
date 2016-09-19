package com.jfjmusic.dllo.baidumusic.controller.fragment.play;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/19.
 */
public class RightPlayFragment extends AbsBaseFragment{

    public static RightPlayFragment newInstance() {

        Bundle args = new Bundle();

        RightPlayFragment fragment = new RightPlayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_play_right;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
