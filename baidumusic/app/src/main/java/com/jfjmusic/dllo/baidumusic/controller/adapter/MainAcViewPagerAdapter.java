package com.jfjmusic.dllo.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class MainAcViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<AbsBaseFragment> fragments;
    private List<String> title;

    //带context的构造方法
    public MainAcViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    //设置Fragment集合
    public void setFragments(List<AbsBaseFragment> fragments) {
        this.fragments = fragments;
    }

    //设置标题集合
    public void setTitle(List<String> title) {
        this.title = title;
    }

    //不带Context的构造方法
    public MainAcViewPagerAdapter(FragmentManager fm) {
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
