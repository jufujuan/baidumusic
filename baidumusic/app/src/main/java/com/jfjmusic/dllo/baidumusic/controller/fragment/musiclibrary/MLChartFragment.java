package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.MLChartListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库--->排行
 */
public class MLChartFragment extends AbsBaseFragment {



    private MLChartListViewAdapter mListViewAdapter;
    //private List<MLChartBean> datas;
    private List<MLChartBean.ContentBean> contentBeens;
    private ListView listView;

    private Gson gson;

    public static MLChartFragment newInstance() {

        Bundle args = new Bundle();

        MLChartFragment fragment = new MLChartFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_chart;
    }

    @Override
    protected void initViews() {
        listView=byView(R.id.ml_chart_listview);
        //datas=new ArrayList<>();
        mListViewAdapter=new MLChartListViewAdapter(context);


    }

    @Override
    protected void initDatas() {

//        List<String> title=new ArrayList<>();
//        title.add("微微一笑很倾城");
//        title.add("下一秒");
//        title.add("江湖");
//        for (int i = 0; i <13 ; i++) {
//            datas.add(new MLChartBean(R.mipmap.new_song,"新歌榜",title));
//        }
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                T.show("新歌榜被点击了",2000);
//                Intent intent=new Intent();
//                intent.setAction(Unique.MAIN_AC_ACTION);
//                intent.putExtra("type",Unique.MUSICL_CHART_PLAY_TYPE);
//                context.sendBroadcast(intent);
//            }
//        });

        //获取网络数据
        getNetDatas();



    }
    //获取网络数据
    protected void getNetDatas(){
        VolleyInstance.getVolleyInstance().startRequest(Unique.ML_CHART_URL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("排行"+resultStr);
                gson=new Gson();
                MLChartBean datas= gson.fromJson(resultStr,MLChartBean.class);
                if (datas==null){
                    L.d("数集为空");
                }else{
                    L.d("数集不为空");
                }
                contentBeens=datas.getContent();
                L.d("集合的大小为:"+contentBeens.size());
                L.d("榜单名:"+contentBeens.get(0).getName());
                L.d("集合下的子集的大小:"+contentBeens.get(1).getContent().size());

                mListViewAdapter.setDatas(contentBeens);
                listView.setAdapter(mListViewAdapter);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
