package com.jfjmusic.dllo.baidumusic.controller.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MICurrentPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MILocalMusicFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MainFragment;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

public class MainActivity extends AbsBaseActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private MainFragment mainFragment;
    //定义广播接受者
    private MyBroadReceiver myBroadReceiver;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mainFragment = new MainFragment();
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
        mTransaction.commit();

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
                        //如果是"本地音乐"传来的广播
                        mineTransaction.replace(R.id.main_framelayout, new MILocalMusicFragment());
                        break;
                    case Unique.MINE_CURRENT_PLAY_TYPE:
                        //如果是"最近播放"传来的广播
                        mineTransaction.replace(R.id.main_framelayout, new MICurrentPlayFragment());
                        break;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 注销广播
         */
        unregisterReceiver(myBroadReceiver);
    }
}
