package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;

/**
 * Created by dllo on 16/9/13.
 */
public class MiLocalAlbumFragment extends AbsBaseFragment{
    public static MiLocalAlbumFragment newInstance() {

        Bundle args = new Bundle();

        MiLocalAlbumFragment fragment = new MiLocalAlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_album;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
