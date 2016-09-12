package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.MusicLibraryFraViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库界面
 */
public class MusicLibraryFragment extends AbsBaseFragment{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MusicLibraryFraViewPagerAdapter adapter;
    private List<String> title;
    private List<Fragment> fragments;

    public static MusicLibraryFragment newInstance() {

        Bundle args = new Bundle();

        MusicLibraryFragment fragment = new MusicLibraryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_library;
    }

    @Override
    protected void initViews() {
        mTabLayout=byView(R.id.music_library_tablayout);
        mViewPager=byView(R.id.music_library_viewpager);
        title=new ArrayList<>();
        fragments=new ArrayList<>();
        /**
         * fragment嵌套fragment要用getChildFragmentManager()而不是getFragmentManager()
         */
        adapter=new MusicLibraryFraViewPagerAdapter(getChildFragmentManager(),context);
    }

    @Override
    protected void initDatas() {
        title.add("推荐");
        title.add("排行");
        title.add("歌单");
        title.add("电台");
        title.add("MV");
        fragments.add(MLRecommendFragment.newInstance());
        fragments.add(MLChartFragment.newInstance());
        fragments.add(MLSongListFragment.newInstance());
        fragments.add(MLRadioFragment.newInstance());
        fragments.add(MLMvFragment.newInstance());
        adapter.setTitle(title);
        adapter.setFragments(fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
