package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLMvRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLMvBean;
import com.jfjmusic.dllo.baidumusic.utils.MVNewAndHot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/16.
 * 乐库-->MV-->最新界面
 * 复用的界面
 */
public class MLMvRecylerFragment extends AbsBaseFragment{

    private RecyclerView mRecyclerView;
    private MLMvRecyclerAdapter mAdapter;
    private List<MLMvBean.ResultBean.MvListBean> datas;
    /**
     * 这里实现了复用机制(传入实体类的参数)
     */
    public static MLMvRecylerFragment newInstance(Bundle bundle) {
        Bundle args = new Bundle();
        args.putBundle("beans",bundle);
       // args.putString("type",type.toString());
        MLMvRecylerFragment fragment = new MLMvRecylerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_mv_new_hot;
    }

    @Override
    protected void initViews() {
        datas=new ArrayList<>();
        mRecyclerView=byView(R.id.fra_ml_mv_recyclerview);
        mAdapter=new MLMvRecyclerAdapter(context);
    }

    @Override
    protected void initDatas() {
        Bundle args=getArguments();
        Bundle bundle=args.getBundle("beans");
        String type=args.getString("type");

        datas= (List<MLMvBean.ResultBean.MvListBean>) bundle.getSerializable("datas");

        mAdapter.setDatas(datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
    }

}
