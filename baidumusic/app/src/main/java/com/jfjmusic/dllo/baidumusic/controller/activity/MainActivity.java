package com.jfjmusic.dllo.baidumusic.controller.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MainFragment;
import com.jfjmusic.dllo.baidumusic.utils.OnSwitchpaperListener;

public class MainActivity extends AbsBaseActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private MainFragment mainFragment;



    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mainFragment = new MainFragment();
    }

    @Override
    protected void initDatas() {
        /**
         * 替换占位布局
         */
        mTransaction.replace(R.id.main_framelayout, mainFragment);
        mTransaction.commit();
    }


}
