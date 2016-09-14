package com.jfjmusic.dllo.baidumusic.controller.fragment.ktv;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.KtvListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.KtvAllSingBean;
import com.jfjmusic.dllo.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * K歌界面
 */
public class KtvFragment extends AbsBaseFragment {

    private MyListView listView;
    private List<KtvAllSingBean> datas;
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
        datas=new ArrayList<>();
        mAdapter=new KtvListViewAdapter(context);
    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 10; i++) {
            datas.add(new KtvAllSingBean("逆战-张杰",15469));
        }
        mAdapter.setDatas(datas);
        listView.setAdapter(mAdapter);
    }
}
