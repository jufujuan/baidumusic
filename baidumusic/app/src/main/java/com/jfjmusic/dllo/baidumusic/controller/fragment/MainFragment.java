package com.jfjmusic.dllo.baidumusic.controller.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.MainFraViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.ktv.KtvFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.liveradio.LiveRadioFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.mine.MineFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary.MusicLibraryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 主界面替换占位布局的的Fragment
 */
public class MainFragment extends AbsBaseFragment{
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
    MainFraViewPagerAdapter mAdapter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_main_ac_content;
    }

    @Override
    protected void initViews() {
        mTabLayout=byView(R.id.main_tablayout);
        mViewPager=byView(R.id.main_viewpager);
        fragments=new ArrayList<>();
        title=new ArrayList<>();
        mAdapter=new MainFraViewPagerAdapter(getChildFragmentManager(),context);
    }

    @Override
    protected void initDatas() {
        fragments.add(MineFragment.newInstance());
        fragments.add(MusicLibraryFragment.newInstance());
        fragments.add(KtvFragment.newInstance());
        fragments.add(LiveRadioFragment.newInstance());

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
