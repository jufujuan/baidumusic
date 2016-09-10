package com.jfjmusic.dllo.baidumusic.controller.activity;



import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.MainAcViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.KtvFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.LiveRadioFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MineFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MusicLibraryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity{

    /**
     * 布局中控件的声明
     */
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    /**
     * fragment的集合
     * title的集合
     */
    List<AbsBaseFragment> fragments;
    List<String> title;

    /**
     * viewpager的适配器
     */
    MainAcViewPagerAdapter mAdapter;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mTabLayout=byView(R.id.main_tablayout);
        mViewPager=byView(R.id.main_viewpager);
        fragments=new ArrayList<>();
        title=new ArrayList<>();
        mAdapter=new MainAcViewPagerAdapter(getSupportFragmentManager(),this);
    }

    @Override
    protected void initDatas() {
        fragments.add(new MineFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(new KtvFragment());
        fragments.add(new LiveRadioFragment());

        title.add("我的");
        title.add("乐库");
        title.add("k歌");
        title.add("直播");

        mAdapter.setFragments(fragments);
        mAdapter.setTitle(title);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
