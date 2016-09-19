package com.jfjmusic.dllo.baidumusic.controller.fragment.play;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/19.
 */
public class LeftPlayFragment extends AbsBaseFragment{

    public static LeftPlayFragment newInstance() {

        Bundle args = new Bundle();

        LeftPlayFragment fragment = new LeftPlayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_play_left;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
