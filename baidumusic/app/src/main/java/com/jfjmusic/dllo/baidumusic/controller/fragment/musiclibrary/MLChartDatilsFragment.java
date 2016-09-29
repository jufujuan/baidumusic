package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.MLChartDetailsListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.MLChartListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartBean;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartDetailsBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库--->排行
 */
public class MLChartDatilsFragment extends AbsBaseFragment implements View.OnClickListener {

    private MLChartListViewAdapter mListViewAdapter;
    //private List<MLChartBean> datas;
    private List<MLChartBean.ContentBean> contentBeens;
    private ListView listView;
    private ImageView backBtn;
    //   private ChartDetailsBroadReceiver myBroadReceiver;
    private Gson gson;
    private StringBuffer buffer;
    private String urlType;
    private String url;
    private MLChartDetailsBean mlChartDetailsBean;
    private ImageView topImg;
    private MLChartDetailsListViewAdapter mAdpter;
    private TextView countTv;

    public static MLChartDatilsFragment newInstance(String urlType) {

        Bundle args = new Bundle();
        args.putString("type", urlType);
        MLChartDatilsFragment fragment = new MLChartDatilsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fra_ml_chart_details;
    }

    @Override
    protected void initViews() {
        backBtn = byView(R.id.rank_detail_back_btn);
        backBtn.setOnClickListener(this);
        buffer = new StringBuffer(Unique.CHART_DETAILS_URL);
        topImg = byView(R.id.fra_ml_chart_details_top_bg);
        listView=byView(R.id.fra_ml_chart_details_song_listview);
        mAdpter=new MLChartDetailsListViewAdapter(context);
        countTv=byView(R.id.fra_ml_chart_details_song_count);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        urlType = bundle.getString("type");

        if (urlType.isEmpty()) {
            L.d("排行详情界面没有得到的值");
        } else {
            int start = buffer.indexOf("参数", 0);
            buffer.replace(start, start + 2, urlType);
            url = buffer.toString();
            L.d("获得接口网址" + url);
        }
        getNetDatas(url);

    }

    //获取网络数据
    protected void getNetDatas(String urlStr) {
        VolleyInstance.getVolleyInstance().startRequest(urlStr, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                gson = new Gson();
                mlChartDetailsBean = gson.fromJson(resultStr, MLChartDetailsBean.class);
                Picasso.with(context).load(mlChartDetailsBean.getBillboard().getPic_s444()).into(topImg);
                mAdpter.setDatas(mlChartDetailsBean.getSong_list());
                listView.setAdapter(mAdpter);
                countTv.setText("共有"+String.valueOf(mlChartDetailsBean.getSong_list().size())+"首歌");
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank_detail_back_btn:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
