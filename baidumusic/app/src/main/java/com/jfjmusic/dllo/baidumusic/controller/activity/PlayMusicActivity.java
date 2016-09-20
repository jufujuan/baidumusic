package com.jfjmusic.dllo.baidumusic.controller.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.PlayAcViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.LeftPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.MiddlePlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.RightPlayFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRadioBean;
import com.jfjmusic.dllo.baidumusic.model.bean.PlayBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import net.qiujuer.genius.blur.StackBlur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 音乐播放界面
 */
public class PlayMusicActivity extends AbsBaseActivity{

    private Bitmap mBitmap, mCompressBitmap;
    private ImageView test;
    private LinearLayout root;
    private List<Fragment> fragments;
    private PlayAcViewPagerAdapter playAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected int setLayout() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initViews() {
        fragments=new ArrayList<>();
        playAdapter=new PlayAcViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager = (ViewPager) findViewById(R.id.ac_play_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.ac_play_Tablayout);

        root = (LinearLayout) findViewById(R.id.ac_play_music_root);
       // initBlur();
    }

    @Override
    protected void initDatas() {
        //applyBlur();
        fragments.add(LeftPlayFragment.newInstance());
        fragments.add(MiddlePlayFragment.newInstance());
        fragments.add(RightPlayFragment.newInstance());
        playAdapter.setFragments(fragments);
        viewPager.setAdapter(playAdapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < fragments.size(); i++) {
            tabLayout.getTabAt(i).setCustomView(playAdapter.getTabView(i));
        }






    }


    /**
     * 实现高斯模糊
     */
//    private void initBlur() {
//       // 找到图片
//        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.new_song);
//
//        // Init src image
//        //test.setImageBitmap(mBitmap);
//
//        // 压缩并保存位图
//        Matrix matrix = new Matrix();
//        matrix.postScale(1.0f / SCALE_FACTOR, 1.0f / SCALE_FACTOR);
//        // 新的压缩位图
//        mCompressBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
//                mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
//        mCompressBitmap=StackBlur.blurNatively(mCompressBitmap, (int) 1, false);
//        //test.setImageBitmap(mCompressBitmap);
//        Drawable drawable =new BitmapDrawable(mCompressBitmap);
//        root.setBackground(drawable);
//    }


}
