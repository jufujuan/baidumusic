package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.utils.OnSwitchpaperListener;

/**
 * Created by dllo on 16/9/10.
 */
public class MineFragment extends AbsBaseFragment{

    private LinearLayout linearLayout;
    private LocalMusicFragment localMusicFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;


    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        linearLayout = byView(R.id.fra_local_music_item);

    }

    @Override
    protected void initDatas() {
        localMusicFragment=new LocalMusicFragment();
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //transaction.replace(R.id.main_framelayout,localMusicFragment,"LocalMusic");
               // transaction.hide(getParentFragment());
                //transaction.add(R.id.main_framelayout,localMusicFragment,"LocalMusic");

               // transaction.addToBackStack("null");
              //  transaction.commit();
            }
        });
    }


}
