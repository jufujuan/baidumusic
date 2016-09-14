package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.MiViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 我的界面中的'本地音乐'
 */
public class MILocalMusicFragment extends AbsBaseFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MiViewPagerAdapter miViewPagerAdapter;
    private List<Fragment> fragments;
    private List<String> title;
    private ImageView backImg;

    public static MILocalMusicFragment newInstance() {

        Bundle args = new Bundle();

        MILocalMusicFragment fragment = new MILocalMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_music;
    }

    @Override
    protected void initViews() {
        tabLayout=byView(R.id.fra_local_music_tablayout);
        viewPager=byView(R.id.fra_local_music_viewpager);
        backImg=byView(R.id.item_top_bar_back_img);
        fragments=new ArrayList<>();
        title=new ArrayList<>();
        miViewPagerAdapter=new MiViewPagerAdapter(getChildFragmentManager(),context);
    }

    @Override
    protected void initDatas() {
        fragments.add(MiLocalSongFragment.newInstance());
        fragments.add(MiLoaclFileFragment.newInstance());
        fragments.add(MiLocalSingerFragment.newInstance());
        fragments.add(MiLocalAlbumFragment.newInstance());
        title.add("歌曲");
        title.add("文件夹");
        title.add("歌手");
        title.add("专辑");
        miViewPagerAdapter.setFragments(fragments);
        miViewPagerAdapter.setTitle(title);
        viewPager.setAdapter(miViewPagerAdapter);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
