package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.widget.ExpandableListView;
import android.widget.ListView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.KtvListViewAdapter;
import com.jfjmusic.dllo.baidumusic.model.bean.KtvAllSingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * K歌界面
 */
public class KtvFragment extends AbsBaseFragment{

    private ExpandableListView listView;
    private List<KtvAllSingBean> datas;
    private KtvListViewAdapter mAdapter;

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
