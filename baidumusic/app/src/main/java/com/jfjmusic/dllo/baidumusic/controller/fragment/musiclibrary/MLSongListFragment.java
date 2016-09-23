package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLSongListRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLSongLIstBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.T;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库-->歌单
 */
public class MLSongListFragment extends AbsBaseFragment {

    private RecyclerView mRecyclerView;
    private MLSongListRecyclerAdapter mAdapter;
    private List<MLSongLIstBean.ContentBean> datas;
    //这个是获取的实体类的全部信息
    private MLSongLIstBean bean;


    public static MLSongListFragment newInstance() {

        Bundle args = new Bundle();

        MLSongListFragment fragment = new MLSongListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_song_list;
    }

    @Override
    protected void initViews() {
        mRecyclerView=byView(R.id.fra_ml_song_list_recyclerview);
        mAdapter=new MLSongListRecyclerAdapter(context);
    }

    @Override
    protected void initDatas() {

        //获得网络数据
        getNetDatas();

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                List<String> songIds=new ArrayList<String>();
                songIds=bean.getContent().get(0).getSongIds();
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
    protected void getNetDatas(){
        VolleyInstance.getVolleyInstance().startRequest(Unique.ML_SONG_LIST_URL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("歌单"+resultStr);
                Gson gson=new Gson();
                MLSongLIstBean bean=gson.fromJson(resultStr,MLSongLIstBean.class);
                datas=bean.getContent();
                mAdapter.setDatas(datas);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
