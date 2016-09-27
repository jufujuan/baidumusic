package com.jfjmusic.dllo.baidumusic.controller.fragment.mine;

import android.os.Bundle;
import android.widget.ListView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.MiLocalAlbumListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MiLocalAlbumBean;

import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * 我的--本地音乐——-专辑界面
 */
public class MiLocalAlbumFragment extends AbsBaseFragment {

    private List<MiLocalAlbumBean> datas;
    private ListView listView;
    private MiLocalAlbumListViewAdapter mAdapter;

    public static MiLocalAlbumFragment newInstance() {

        Bundle args = new Bundle();

        MiLocalAlbumFragment fragment = new MiLocalAlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_album;
    }

    @Override
    protected void initViews() {
        listView=byView(R.id.mi_lcoal_album_listview);
    }

    @Override
    protected void initDatas() {

    }
}
