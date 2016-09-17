package com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MLMvBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.MVNewAndHot;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库-->mv界面
 */
public class MLMvFragment extends AbsBaseFragment {

    private RadioGroup mRadioGroup;
    private List<MLMvBean.ResultBean.MvListBean> datas;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    public static MLMvFragment newInstance() {

        Bundle args = new Bundle();
        MLMvFragment fragment = new MLMvFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ml_mv;
    }

    @Override
    protected void initViews() {
        mRadioGroup = byView(R.id.fra_ml_mv_radiogroup);
    }

    @Override
    protected void initDatas() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        //设置默认显示的mv
        getNetDatas(Unique.ML_MV_NEW_URL);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", (Serializable) datas);
        transaction.replace(R.id.fra_ml_mv_recyclerview, MLMvRecylerFragment.newInstance(bundle));
        transaction.commit();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction fragmentTransaction=manager.beginTransaction();
                Bundle bundle = new Bundle();
                switch (checkedId) {
                    case R.id.fra_ml_mv_radiobutton_new:
                        getNetDatas(Unique.ML_MV_NEW_URL);
                        bundle.putSerializable("datas", (Serializable) datas);
                        fragmentTransaction.replace(R.id.fra_ml_mv_recyclerview, MLMvRecylerFragment.newInstance(bundle));
                        break;
                    case R.id.fra_ml_mv_radiobutton_hot:
                        getNetDatas(Unique.ML_MV_HOT_URL);
                        bundle.putSerializable("datas", (Serializable) datas);
                        fragmentTransaction.replace(R.id.fra_ml_mv_recyclerview, MLMvRecylerFragment.newInstance(bundle));
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    //获取网络数据
    protected void getNetDatas(String urlStr) {
        VolleyInstance.getVolleyInstance().startRequest(urlStr, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("mv" + resultStr);
                Gson gson = new Gson();
                MLMvBean bean = gson.fromJson(resultStr, MLMvBean.class);
                datas = bean.getResult().getMv_list();
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
}
