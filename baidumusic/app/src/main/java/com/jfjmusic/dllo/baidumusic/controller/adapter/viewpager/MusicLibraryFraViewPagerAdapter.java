package com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class MusicLibraryFraViewPagerAdapter extends FragmentPagerAdapter{

    private Context context;
    private List<String> title;
    private List<Fragment> fragments;

    public MusicLibraryFraViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public MusicLibraryFraViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
