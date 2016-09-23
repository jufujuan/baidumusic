package com.jfjmusic.dllo.baidumusic.controller.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.mine.MICurrentPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.mine.MiLocalMusicFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MainFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.PlayBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

public class MainActivity extends AbsBaseActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private MainFragment mainFragment;
    //定义广播接受者
    private MyBroadReceiver myBroadReceiver;
    private PlayBean playBean;
    private LinearLayout textMiniBarLL;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        textMiniBarLL=byView(R.id.ac_main_minibar_text);
        /**
         * 动态注册广播
         */
        registerBroad();


    }

    @Override
    protected void initDatas() {
        /**
         * 默认替换的占位布局
         */
        mTransaction.replace(R.id.main_framelayout, mainFragment);
        /******/
        //mTransaction.addToBackStack(null);
        /******/
        mTransaction.commit();
        textMiniBarLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getNetDatas();
                Intent intent=new Intent(MainActivity.this,PlayMusicActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("playbean",playBean);
//                intent.putExtra("bean",bundle);
                startActivity(intent);
            }
        });

    }

    /**
     * 定义广播接受者
     * 用来fragment多层嵌套下的各种替换占位布局
     */
    class MyBroadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            /**
             * 用于大面积替换占位布局
             */

            if (intent.getAction().equals(Unique.MAIN_AC_ACTION)) {
                FragmentTransaction mineTransaction=mFragmentManager.beginTransaction();
                switch (intent.getIntExtra("type",0)) {
                    case 0:
                        L.d("并没有任何广播设置");
                        break;
                    case Unique.MINE_LOCAL_MUSIC_TYPE:
                        mineTransaction.addToBackStack(null);
                        //如果是"本地音乐"传来的广播
                        mineTransaction.replace(R.id.main_framelayout, MiLocalMusicFragment.newInstance());
                        break;
                    case Unique.MINE_CURRENT_PLAY_TYPE:
                        mineTransaction.addToBackStack(null);
                        //如果是"最近播放"传来的广播
                        mineTransaction.replace(R.id.main_framelayout, MICurrentPlayFragment.newInstance());
                        break;

//                    case Unique.MUSICL_CHART_PLAY_TYPE:
//                        T.show("排行榜传来的广播",2000);
//                        mineTransaction.addToBackStack(null);
//                        mineTransaction.replace(R.id.main_framelayout, MICurrentPlayFragment.newInstance());
//                        break;

                }
                mineTransaction.commit();
            }

        }
    }

    /**
     * 注册广播
     */
    private void registerBroad() {
        //1.实例化广播接受者
        myBroadReceiver = new MyBroadReceiver();
        //2.设置意图过滤
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Unique.MAIN_AC_ACTION);
        //设置优先级
        //intentFilter.setPriority();
        //3.注册广播
        registerReceiver(myBroadReceiver, intentFilter);
    }

    //获取网络数据
    protected void getNetDatas() {
        VolleyInstance.getVolleyInstance().startRequest(Unique.PLAY_CURRENT_MUSIC, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                L.d("当前播放音乐" + resultStr);
                Gson gson = new Gson();
                playBean = gson.fromJson(resultStr, PlayBean.class);
            }

            @Override
            public void failure() {
                L.d("失败");
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 注销广播
         */
        unregisterReceiver(myBroadReceiver);
    }
}
