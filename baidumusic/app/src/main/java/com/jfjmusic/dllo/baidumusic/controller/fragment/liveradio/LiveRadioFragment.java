package com.jfjmusic.dllo.baidumusic.controller.fragment.liveradio;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.LiveRadioAllRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.LiveRadioClassRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.LiveRadioAllBean;
import com.jfjmusic.dllo.baidumusic.model.bean.LiveRadioClassBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 直播界面
 */
public class LiveRadioFragment extends AbsBaseFragment {

    private List<LiveRadioClassBean.DataBean> classDatas;
    private List<LiveRadioAllBean.DataBean.SubDataBean> hotDatas;
    private LiveRadioClassRecyclerAdapter mClassAdapter;
    private LiveRadioAllRecyclerAdapter mHotAdapter;
    private RecyclerView classRecyclerView,hotRecyclerView;


    public static LiveRadioFragment newInstance() {

        Bundle args = new Bundle();

        LiveRadioFragment fragment = new LiveRadioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_live_radio;
    }

    @Override
    protected void initViews() {
        mClassAdapter=new LiveRadioClassRecyclerAdapter(context);
        mHotAdapter=new LiveRadioAllRecyclerAdapter(context);
        classRecyclerView=byView(R.id.fra_live_radio_class_recyclerview);
        hotRecyclerView=byView(R.id.fra_live_radio_hot_recyclerview);
    }

    @Override
    protected void initDatas() {
        //获取上部分分类的网络数据
        getNetClassDatas();
        mClassAdapter.setDatas(classDatas);
        classRecyclerView.setAdapter(mClassAdapter);
        classRecyclerView.setLayoutManager(new GridLayoutManager(context,4));
        getNetHotDatas();
        mHotAdapter.setDatas(hotDatas);
        hotRecyclerView.setAdapter(mHotAdapter);
        hotRecyclerView.setLayoutManager(new GridLayoutManager(context,2));

    }
    //获得上部分分类的网络数据
    protected void getNetClassDatas(){
        VolleyInstance.getVolleyInstance().startRequest(Unique.LIVERADIO_CLASS, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("直播"+resultStr);
                Gson gson = new Gson();
                LiveRadioClassBean bean = gson.fromJson(resultStr, LiveRadioClassBean.class);
                classDatas = bean.getData();
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
    //获得下部分热门直播的网络数据
    protected void getNetHotDatas(){
        VolleyInstance.getVolleyInstance().startRequest(Unique.LIVERADIO_HOT, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("直播"+resultStr);
                Gson gson = new Gson();
                LiveRadioAllBean bean = gson.fromJson(resultStr, LiveRadioAllBean.class);
                hotDatas = bean.getData().getData();
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
