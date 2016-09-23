package com.jfjmusic.dllo.baidumusic.controller.fragment.ktv;

import android.os.Bundle;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.KtvListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.KtvAllSingBean;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRadioBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;
import com.jfjmusic.dllo.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * K歌界面
 */
public class KtvFragment extends AbsBaseFragment {

    private MyListView listView;
    private List<KtvAllSingBean.ResultBean.ItemsBean> datas;
    private KtvListViewAdapter mAdapter;
    // 最新的写法
    // 向fragment传值 setArguments
    // getArguments
    // 对应的就是Activity传值时的 intent.putExtra
    //                       intent.getXXExtra
    public static KtvFragment newInstance() {
        
        Bundle args = new Bundle();
        
        KtvFragment fragment = new KtvFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ktv;
    }

    @Override
    protected void initViews() {
        listView=byView(R.id.fra_ktv_listview);
        mAdapter=new KtvListViewAdapter(context);
    }

    @Override
    protected void initDatas() {
        L.d("ktv初始化数据");
        getNetDatas();


    }
    //获取网络数据
    protected void getNetDatas() {
        VolleyInstance.getVolleyInstance().startRequest(Unique.KTV_URL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("电台" + resultStr);
                Gson gson = new Gson();
                KtvAllSingBean bean = gson.fromJson(resultStr, KtvAllSingBean.class);
                datas = bean.getResult().getItems();
                mAdapter.setDatas(datas);
                listView.setAdapter(mAdapter);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
