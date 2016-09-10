package com.jfjmusic.dllo.baidumusic.controller.fragment;

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
        adapter=new MusicLibraryFraViewPagerAdapter(getFragmentManager(),context);
    }

    @Override
    protected void initDatas() {
        title.add("推荐");
        title.add("排行");
        title.add("歌单");
        title.add("电台");
        title.add("MV");
        fragments.add(new MLRecommendFragment());
        fragments.add(new MLChartFragment());
        fragments.add(new MLSongListFragment());
        fragments.add(new MLRadioFragment());
        fragments.add(new MLMvFragment());
        adapter.setTitle(title);
        adapter.setFragments(fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);



    }
}
