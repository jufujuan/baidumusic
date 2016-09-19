package com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 播放界面的viewpager的适配器
 */
public class PlayAcViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    private Context context;
    //private TextView textView;

    public PlayAcViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public PlayAcViewPagerAdapter(FragmentManager fm) {
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
    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ac_play, null);
       // textView = (TextView) v.findViewById(R.id.item_ac_play);
        return v;
    }

}
