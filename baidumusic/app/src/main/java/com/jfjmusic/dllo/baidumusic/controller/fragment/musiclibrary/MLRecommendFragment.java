package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.RecommendRotateVpAdater;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRecommendBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.ScreenSizeUtil;
import com.jfjmusic.dllo.baidumusic.utils.Unique;
import java.util.ArrayList;
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
    private ViewPager mViewpager;
    private RecommendRotateVpAdater rotateViewPager;
    private LinearLayout pointLl;// 轮播状态改变的小圆点容器
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;
    private static final int TIME = 3000;
    private FrameLayout frameLayout;

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
        mViewpager=byView(R.id.fra_ml_recommend_viewpager);
        pointLl=byView(R.id.rotate_point_container);
        frameLayout=byView(R.id.fra_ml_recommend_rotate_frame);

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

                //设置轮播图
                // 重新设置轮播图的高度
                ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
                params.height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 4;
                frameLayout.setLayoutParams(params);
                rotateViewPager=new RecommendRotateVpAdater(context);
                rotateViewPager.setDatas(focusDatas);
                mViewpager.setAdapter(rotateViewPager);
                // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
                // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
                mViewpager.setCurrentItem(focusDatas.size() * 100);
                // 开始轮播
                handler = new Handler();
                startRotate();
                // 添加轮播小点
                addPoints();
                // 随着轮播改变小点
                changePoints();
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }

    //随着轮播改变小点
    private void changePoints() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有小点设置为白色
                    for (int i = 0; i < focusDatas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.ic_dot_default_unselected);
                    }
                    // 设置当前位置小点为灰色
                    ImageView iv = (ImageView) pointLl.getChildAt(position % focusDatas.size());
                    iv.setImageResource(R.mipmap.ic_dot_default_selected);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // 添加轮播小点
    private void addPoints() {
        // 有多少张图加载多少个小点
        for (int i = 0; i < focusDatas.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            pointIv.setLayoutParams(params);

            // 设置第0页小点的为灰色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.ic_dot_default_selected);
            } else {
                pointIv.setImageResource(R.mipmap.ic_dot_default_unselected);
            }
            pointLl.addView(pointIv);
        }
    }

    // 开始轮播
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = mViewpager.getCurrentItem();
                mViewpager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }
}
