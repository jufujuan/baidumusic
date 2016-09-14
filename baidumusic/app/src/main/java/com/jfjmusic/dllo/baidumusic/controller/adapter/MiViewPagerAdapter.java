package com.jfjmusic.dllo.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/13.
 */
public class MiViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    private Context context;
    private List<String> title;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public MiViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MiViewPagerAdapter(FragmentManager fm) {
        super(fm);
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
