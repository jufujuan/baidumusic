package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.MLChartListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.nio.Buffer;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库--->排行
 */
public class MLChartFragment extends AbsBaseFragment {

    private MLChartListViewAdapter mListViewAdapter;
    private List<MLChartBean.ContentBean> contentBeens;
    private MLChartBean bean;
    private ListView listView;
    private Gson gson;


    public static MLChartFragment newInstance() {

        Bundle args = new Bundle();

        MLChartFragment fragment = new MLChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_chart;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.ml_chart_listview);
        mListViewAdapter = new MLChartListViewAdapter(context);

    }

    @Override
    protected void initDatas() {

        //获取网络数据
        getNetDatas();
        //榜单点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 发送广播,通知activity的占位布局更换
                 */
                Intent intent = new Intent();
                intent.setAction(Unique.MAIN_AC_ACTION);
                intent.putExtra("type", Unique.ML_CHART_DETAILS);
                intent.putExtra("urlType",String.valueOf(contentBeens.get(position).getType()));
                context.sendBroadcast(intent);

            }
        });
    }

    //获取网络数据
    protected void getNetDatas() {
        VolleyInstance.getVolleyInstance().startRequest(Unique.ML_CHART_URL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("排行" + resultStr);
                gson = new Gson();
                bean = gson.fromJson(resultStr, MLChartBean.class);
                if (bean == null) {
                    L.d("数集为空");
                } else {
                    L.d("数集不为空");
                }
                contentBeens = bean.getContent();
                mListViewAdapter.setDatas(contentBeens);
                listView.setAdapter(mListViewAdapter);
            }
            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }




}
