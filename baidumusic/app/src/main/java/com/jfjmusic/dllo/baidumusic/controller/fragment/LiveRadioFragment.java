package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInatance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;

/**
 * Created by dllo on 16/9/10.
 */
public class LiveRadioFragment extends AbsBaseFragment{
    private String url="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";

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

    }

    @Override
    protected void initDatas() {
        //获取网络数据
        getNetDatas();
    }
    protected void getNetDatas(){
        VolleyInatance.getVolleyInatance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("直播"+resultStr);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
