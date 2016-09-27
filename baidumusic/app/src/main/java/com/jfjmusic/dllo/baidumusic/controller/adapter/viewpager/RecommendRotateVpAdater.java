package com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRecommendBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 * 乐库-->轮播图——>的适配器
 */
public class RecommendRotateVpAdater extends PagerAdapter {

    private List<MLRecommendBean.ResultBean.FocusBean.FocusResultBean> datas;
    private Context context;
    private LayoutInflater inflater;

    public RecommendRotateVpAdater(List<MLRecommendBean.ResultBean.FocusBean.FocusResultBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public RecommendRotateVpAdater(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<MLRecommendBean.ResultBean.FocusBean.FocusResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // 为了让ViewPager到最后一页不会像翻书一样回到第一页
        // 设置页数为int最大值,这样向下滑动永远都是下一页
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // position是int最大值所以这里可能是几百甚至上千,因此取余避免数组越界
        int newPosition = position % datas.size();
        View convertView = inflater.inflate(R.layout.item_fra_ml_recommend_rotate, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_iv);
        Picasso.with(context).load(datas.get(newPosition).getRandpic()).into(imageView);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}