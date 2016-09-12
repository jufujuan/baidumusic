package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInatance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;

/**
 * Created by dllo on 16/9/10.
 */
public class MLSongListFragment extends AbsBaseFragment{

    private String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";

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

    }

    @Override
    protected void initDatas() {

        //获得网络数据
        getNetDatas();
    }
    protected void getNetDatas(){
        VolleyInatance.getVolleyInatance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("歌单"+resultStr);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
