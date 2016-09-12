package com.jfjmusic.dllo.baidumusic.controller.fragment;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInatance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;

/**
 * Created by dllo on 16/9/10.
 */
public class MLRadioFragment extends AbsBaseFragment{
    private String url="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.scene.getCategoryScene&category_id=0";
    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_radio;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

        //获取网络数据
        getNetDatas();
    }
    //获取网络数据
    protected void getNetDatas(){
        VolleyInatance.getVolleyInatance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("电台"+resultStr);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
