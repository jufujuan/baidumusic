package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/12.
 */
public class MILocalMusicFragment extends AbsBaseFragment{



    public static MILocalMusicFragment newInstance() {

        Bundle args = new Bundle();

        MILocalMusicFragment fragment = new MILocalMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_music;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
