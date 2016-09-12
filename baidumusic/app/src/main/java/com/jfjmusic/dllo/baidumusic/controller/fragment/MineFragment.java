package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

/**
 * Created by dllo on 16/9/10.
 */
public class MineFragment extends AbsBaseFragment implements View.OnClickListener {

    private LinearLayout localmusicLinearLayout;
    private LinearLayout currentplayLinearLayout;
    private MILocalMusicFragment localMusicFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        localmusicLinearLayout = byView(R.id.fra_local_music_item);
        currentplayLinearLayout=byView(R.id.fra_current_play_item);
    }

    @Override
    protected void initDatas() {
        localMusicFragment=new MILocalMusicFragment();
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        //各种点击事件进入二级界面
        enterNextFragmentListener();
    }

    private void enterNextFragmentListener() {
        /**
         * 本地音乐的点击事件
         */
        localmusicLinearLayout.setOnClickListener(this);
        currentplayLinearLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        /**
         * 发送广播,通知activity的占位布局更换
         */
        Intent intent=new Intent();
        intent.setAction(Unique.MAIN_AC_ACTION);
        switch (v.getId()){
            case R.id.fra_local_music_item:
                intent.putExtra("type",Unique.MINE_LOCAL_MUSIC_TYPE);
                break;
            case R.id.fra_current_play_item:
                intent.putExtra("type",Unique.MINE_CURRENT_PLAY_TYPE);
                break;
        }
        context.sendBroadcast(intent);
    }
}
