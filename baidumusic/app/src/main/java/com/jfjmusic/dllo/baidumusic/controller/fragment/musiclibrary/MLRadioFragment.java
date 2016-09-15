package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRadioRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLSongListRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartBean;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRadioBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库--->电台---的界面
 */
public class MLRadioFragment extends AbsBaseFragment {

    private RecyclerView recyclerView;
    private MLRadioRecyclerAdapter mAdapter;
    private List<MLRadioBean.ResultBean> datas;

    public static MLRadioFragment newInstance() {

        Bundle args = new Bundle();

        MLRadioFragment fragment = new MLRadioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_radio;
    }

    @Override
    protected void initViews() {
        recyclerView = byView(R.id.fra_ml_radio_recyclerview);
        mAdapter = new MLRadioRecyclerAdapter(context);
    }

    @Override
    protected void initDatas() {
        //获取网络数据
        getNetDatas();
        mAdapter.setDatas(datas);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
    }

    //获取网络数据
    protected void getNetDatas() {
        VolleyInstance.getVolleyInstance().startRequest(Unique.ML_RADIO_URL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("电台" + resultStr);
                Gson gson = new Gson();
                MLRadioBean bean = gson.fromJson(resultStr, MLRadioBean.class);
                datas = bean.getResult();
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
