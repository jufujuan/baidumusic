package com.jfjmusic.dllo.baidumusic.controller.fragment.liveradio;

import android.os.Bundle;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

/**
 * Created by dllo on 16/9/10.
 * 直播界面
 */
public class LiveRadioFragment extends AbsBaseFragment {

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
        VolleyInstance.getVolleyInstance().startRequest(Unique.LIVERADIO_CLASS, new VolleyResult() {
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
