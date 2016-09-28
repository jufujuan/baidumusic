package com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 播放界面的viewpager的适配器
 */
public class PlayAcViewPagerAdapter extends PagerAdapter {

//    private List<Fragment> fragments;
    private List<LinearLayout> linearLayouts;
    private Context context;

    public PlayAcViewPagerAdapter(List<LinearLayout> linearLayouts) {
        this.linearLayouts = linearLayouts;
        notifyDataSetChanged();
    }

    /**
     * 需要滑动的控件的数量
     * @return
     */
    @Override
    public int getCount() {
        return linearLayouts.size();
    }

    /**
     * 判断显示的是否是同一个LinearLayout
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 当要显示的linearlayout可以进行缓存的时候,就会调用这个方法显示linearlayout的初始化,我们将要显示的LinearLayout加入到ViewGronp中
     * 然后作为返回值返回即可
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = linearLayouts.get(position);
        container.addView(v);
        return v;
    }
    /**
     * pageradpater只缓存三个要显示的LinearLayout,如果滑动的图片超过了缓存的范围
     * 就会调用这个方法,将LinearLayout销毁
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(linearLayouts.get(position));
    }
}
