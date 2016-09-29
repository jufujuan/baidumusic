package com.jfjmusic.dllo.baidumusic.controller.fragment.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.MiDownLoadViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.MiLocalViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/28.
 * 我的---下载管理--界面
 */
public class MiDownLoadFragment extends AbsBaseFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MiDownLoadViewPagerAdapter mAdapter;
    private List<Fragment> fragments;
    private List<String> title;
    private ImageView backImg;

    public static MiDownLoadFragment newInstance() {

        Bundle args = new Bundle();

        MiDownLoadFragment fragment = new MiDownLoadFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_download_music;
    }

    @Override
    protected void initViews() {
        tabLayout=byView(R.id.fra_download_music_tablayout);
        viewPager=byView(R.id.fra_download_music_viewpager);
        backImg=byView(R.id.item_download_top_bar_back_img);
        fragments=new ArrayList<>();
        title=new ArrayList<>();
        mAdapter =new MiDownLoadViewPagerAdapter(getChildFragmentManager(),context);
    }

    @Override
    protected void initDatas() {
        fragments.add(MiLocalSongFragment.newInstance());
        fragments.add(MiLoaclFileFragment.newInstance());
        title.add("已下载");
        title.add("正在下载");
        mAdapter.setFragments(fragments);
        mAdapter.setTitle(title);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show("点击了返回键",2000);
                //FragmentManager manager=getFragmentManager();
                //manager.popBackStack();

                getFragmentManager().popBackStack();
                // getFragmentManager().getBackStackEntryAt(0);
            }
        });
    }
}
