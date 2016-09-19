package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendMix5RecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendMix9RecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendMod7RecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendRadioRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendRecsongRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendSceneRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommentDiyRecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommentMix1RecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommentMix22RecyclerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview.MLRecommendEntryRecyclerAdpater;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRecommendBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库————>推荐————界面
 */
public class MLRecommendFragment extends AbsBaseFragment {

    private List<MLRecommendBean.ResultBean.Mix1Bean.Mix1ResultBean> mix1Datas;//新碟上架
    private List<MLRecommendBean.ResultBean.FocusBean.FocusResultBean> focusDatas;//轮播图
    private List<MLRecommendBean.ResultBean.Mix22Bean.Mix22ResultBean> mix22Datas;//热销专辑
    private List<MLRecommendBean.ResultBean.EntryBean.EntryResultBean> entryDatas;//轮播图下三个小图标
    private List<MLRecommendBean.ResultBean.SceneBean.SceneResultBean.ActionBean> sceneDatas;//场景电台
    private List<MLRecommendBean.ResultBean.Mix5Bean.Mix5ResultBean> mix5Datas;//最热推荐
    private List<MLRecommendBean.ResultBean.RecsongBean.RecsongResultBean> recsongDatas;//今日推荐
    private List<MLRecommendBean.ResultBean.RadioBean.RadioResultBean> radioDatas;//乐播节目
    private List<MLRecommendBean.ResultBean.DiyBean.DiyResultBean> diyDatas;//新碟上架
    private List<MLRecommendBean.ResultBean.Mod7Bean.Mod7ResultBean> mod7Datas;//专栏
    private List<MLRecommendBean.ResultBean.Mix9Bean.Mix9ResultBean> mix9Datas;//原创音乐
    private List<MLRecommendBean.ModuleBean> ModulDatas;
    private RecyclerView recsongRecyclerView,mod7RecyclerView,mix1Recyclerview,mix9RecyclerView,entryRecyclerView,mix22RecyclerView,sceneRecyclerView,mix5RecyclerView,radioRecyclerView,diyRecyclerView;
    private MLRecommendEntryRecyclerAdpater entryAdapter;
    private MLRecommentDiyRecyclerAdapter diyAdapter;
    private MLRecommentMix1RecyclerAdapter mix1Adapter;
    private MLRecommentMix22RecyclerAdapter mix22Adapter;
    private MLRecommendSceneRecyclerAdapter sceneAdapter;
    private MLRecommendRecsongRecyclerAdapter recsongAdapter;
    private MLRecommendMix9RecyclerAdapter mix9Adapter;
    private MLRecommendMix5RecyclerAdapter mix5Adapter;
    private MLRecommendRadioRecyclerAdapter radioAdapter;
    private MLRecommendMod7RecyclerAdapter mod7Adapter;

    public static MLRecommendFragment newInstance() {

        Bundle args = new Bundle();

        MLRecommendFragment fragment = new MLRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_recommecd;
    }

    @Override
    protected void initViews() {
        mix1Recyclerview=byView(R.id.fra_ml_recommend_mix1_recyclerview);
        entryRecyclerView=byView(R.id.fra_ml_recommend_entry_recyclerView);
        mix22RecyclerView=byView(R.id.fra_ml_recommend_mix22_recyclerview);
        sceneRecyclerView=byView(R.id.fra_ml_recommend_scene_recyclerview);
        mix5RecyclerView=byView(R.id.fra_ml_recommend_mix5_recyclerview);
        radioRecyclerView=byView(R.id.fra_ml_recommend_radio_recyclerview);
        diyRecyclerView=byView(R.id.fra_ml_recommend_diy_recyclerview);
        recsongRecyclerView=byView(R.id.fra_ml_recommend_recsong_recyclerview);
        mod7RecyclerView=byView(R.id.fra_ml_recommend_mod7_recyclerview);
        mix9RecyclerView=byView(R.id.fra_ml_recommend_mix9_recyclerview);

        entryAdapter=new MLRecommendEntryRecyclerAdpater(context);
        diyAdapter=new MLRecommentDiyRecyclerAdapter(context);
        mix1Adapter=new MLRecommentMix1RecyclerAdapter(context);
        mix22Adapter=new MLRecommentMix22RecyclerAdapter(context);
        sceneAdapter=new MLRecommendSceneRecyclerAdapter(context);
        recsongAdapter=new MLRecommendRecsongRecyclerAdapter(context);
        mix9Adapter=new MLRecommendMix9RecyclerAdapter(context);
        mix5Adapter=new MLRecommendMix5RecyclerAdapter(context);
        radioAdapter=new MLRecommendRadioRecyclerAdapter(context);
        mod7Adapter=new MLRecommendMod7RecyclerAdapter(context);
    }

    @Override
    protected void initDatas() {
        //获取网络数据
        getNetDatas();
        //轮播图下边的三个小图标
        entryAdapter.setDatas(entryDatas);
        entryRecyclerView.setAdapter(entryAdapter);
        entryRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //歌单推荐
        diyAdapter.setDatas(diyDatas);
        diyRecyclerView.setAdapter(diyAdapter);
        diyRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //新碟上架
        mix1Adapter.setDatas(mix1Datas);
        mix1Recyclerview.setAdapter(mix1Adapter);
        mix1Recyclerview.setLayoutManager(new GridLayoutManager(context,3));
        //热销专辑
        mix22Adapter.setDatas(mix22Datas);
        mix22RecyclerView.setAdapter(mix22Adapter);
        mix22RecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //场景电台
        sceneAdapter.setDatas(sceneDatas);
        sceneRecyclerView.setAdapter(sceneAdapter);
        sceneRecyclerView.setLayoutManager(new GridLayoutManager(context,4));
        //今日推荐歌曲
        recsongAdapter.setDatas(recsongDatas);
        recsongRecyclerView.setAdapter(recsongAdapter);
        recsongRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //原创音乐
        mix9Adapter.setDatas(mix9Datas);
        mix9RecyclerView.setAdapter(mix9Adapter);
        mix9RecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //最热MV
        mix5Adapter.setDatas(mix5Datas);
        mix5RecyclerView.setAdapter(mix5Adapter);
        mix5RecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //乐播节目
        radioAdapter.setDatas(radioDatas);
        radioRecyclerView.setAdapter(radioAdapter);
        radioRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //专栏
        mod7Adapter.setDatas(mod7Datas);
        mod7RecyclerView.setAdapter(mod7Adapter);
        mod7RecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    protected void getNetDatas(){
        VolleyInstance.getVolleyInstance().startRequest(Unique.ML_RECOMMEND, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("推荐"+resultStr);
                Gson gson = new Gson();
                MLRecommendBean bean = gson.fromJson(resultStr, MLRecommendBean.class);
                mix1Datas = bean.getResult().getMix_1().getResult();
                focusDatas=bean.getResult().getFocus().getResult();
                mix22Datas=bean.getResult().getMix_22().getResult();
                entryDatas=bean.getResult().getEntry().getResult();
                sceneDatas=bean.getResult().getScene().getResult().getAction();
                mix5Datas=bean.getResult().getMix_5().getResult();
                recsongDatas=bean.getResult().getRecsong().getResult();
                radioDatas=bean.getResult().getRadio().getResult();
                diyDatas=bean.getResult().getDiy().getResult();
                mod7Datas=bean.getResult().getMod_7().getResult();
                mix9Datas=bean.getResult().getMix_9().getResult();
                ModulDatas=bean.getModule();
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
