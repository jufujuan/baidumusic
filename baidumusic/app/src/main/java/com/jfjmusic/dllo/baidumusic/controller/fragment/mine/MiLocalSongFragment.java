package com.jfjmusic.dllo.baidumusic.controller.fragment.mine;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ListView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.listview.MiLocalSongListViewAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.AbsBaseFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.LocalMusicBean;
import com.jfjmusic.dllo.baidumusic.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * "我的"-->"本地音乐"-->"歌曲"
 */
public class MiLocalSongFragment extends AbsBaseFragment {

    private ListView mListView;
    private MiLocalSongListViewAdapter mAdapter;
    private List<LocalMusicBean> datas;

    public static MiLocalSongFragment newInstance() {

        Bundle args = new Bundle();

        MiLocalSongFragment fragment = new MiLocalSongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mi_local_song;
    }

    @Override
    protected void initViews() {
        mListView=byView(R.id.fra_mi_local_song_listview);
        mAdapter=new MiLocalSongListViewAdapter(context);
        datas=new ArrayList<>();
    }

    @Override
    protected void initDatas() {
        Cursor cursor=context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null,null);
        L.d("游标的个数"+cursor.getCount());
        while (cursor.moveToNext()){
            int titleIndex=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int singerIndex=cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int timeIndex=cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int urlIndex=cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            String title=cursor.getString(titleIndex);
            String singer=cursor.getString(singerIndex);
            long duration=cursor.getLong(timeIndex);
            String url=cursor.getString(urlIndex);
            datas.add(new LocalMusicBean(singer,title,duration,url));
        }
        mAdapter.setDatas(datas);
        mListView.setAdapter(mAdapter);

    }
}
