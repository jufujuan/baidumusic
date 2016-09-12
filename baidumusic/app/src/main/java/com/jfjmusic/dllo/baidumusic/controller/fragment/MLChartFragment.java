package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.widget.ListView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.MLChartListViewAdapter;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInatance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库中的排行界面
 */
public class MLChartFragment extends AbsBaseFragment{

    private MLChartListViewAdapter mListViewAdapter;
    private List<MLChartBean> datas;
    private ListView listView;
    private String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";

    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_chart;
    }

    @Override
    protected void initViews() {
        listView=byView(R.id.ml_chart_listview);
        datas=new ArrayList<>();
        mListViewAdapter=new MLChartListViewAdapter(context);
    }

    @Override
    protected void initDatas() {
        List<String> title=new ArrayList<>();
        title.add("微微一笑很倾城");
        title.add("下一秒");
        title.add("江湖");
        for (int i = 0; i <13 ; i++) {
            datas.add(new MLChartBean(R.mipmap.new_song,"新歌榜",title));
        }
        mListViewAdapter.setDatas(datas);
        listView.setAdapter(mListViewAdapter);
        //获取网络数据
        getNetDatas();
    }
    //获取网络数据
    protected void getNetDatas(){
        VolleyInatance.getVolleyInatance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("排行"+resultStr);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
