package com.jfjmusic.dllo.baidumusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.PlayAcViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.LeftPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.MiddlePlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.RightPlayFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.MusicBean;
import com.jfjmusic.dllo.baidumusic.model.bean.PlayBean;
import com.jfjmusic.dllo.baidumusic.utils.MusicService;

import net.qiujuer.genius.blur.StackBlur;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/9/19.
 * 音乐播放界面
 */
public class PlayMusicActivity extends AbsBaseActivity implements View.OnClickListener {

    private static final int SCALE_FACTOR = 6;
    private Bitmap mBitmap, mCompressBitmap;
    private ImageView test;
    private LinearLayout root;//背景
    private List<Fragment> fragments;
    private PlayAcViewPagerAdapter playAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private ImageView pauseBtn, nextBtn, beforBtn;
    private TextView titleTv, nameTv ,timeTv;
    private SeekBar seekBar;
    private Intent getIntent;

    /**
     * 服务相关的声明
     */
    private MusicService musicService;
    private MusicService.MusicBinder binder;
    private ServiceConnection conn;

    // 播放器相关
    // 随时通知进度条刷新的Runnable
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    int pos = (int) msg.obj;
                    Log.d("MainActivity", "pos:" + pos);
                    seekBar.setProgress(pos);
                    syncDuration();
                    break;
                case 101:

                    break;

            }
            return false;
        }
    });

    private class NotifySeekBarRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (binder != null && binder.getPlayerState()) {
                    Message msg = new Message();
                    msg.what = 100;
                    msg.obj = binder.getCurrentPos();
                    Log.d("NotifySeekBarRunnable", "msg.obj:" + msg.obj);
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }





    @Override
    protected int setLayout() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initViews() {
        fragments = new ArrayList<>();
        playAdapter = new PlayAcViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager = byView(R.id.ac_play_viewpager);
        tabLayout = byView(R.id.ac_play_Tablayout);

        root = byView(R.id.ac_play_music_root);
        pauseBtn = byView(R.id.ac_play_pause);
        nextBtn = byView(R.id.ac_play_next);
        beforBtn = byView(R.id.ac_play_before);
        seekBar = byView(R.id.ac_play_seekbar);
        titleTv = byView(R.id.ac_play_song_title);
        nameTv = byView(R.id.ac_play_song_name);
        timeTv=byView(R.id.ac_play_song_all_time);
        // 获得从上一个activity中获取的数据
        getIntent = getIntent();
        Bundle bundle = getIntent.getBundleExtra("bean");
        PlayBean bean = (PlayBean) bundle.getSerializable("playbean");
    }

    @Override
    protected void initDatas() {
        //applyBlur();
        addTab();
        pauseBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        beforBtn.setOnClickListener(this);


     /******************************************/
        // 开启服务
        initService();
        // 初始化SeekBar
        initSeekBar();



    }
    private void initSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (binder != null) {
                        binder.setCurrentPos(progress);
                        seekBar.setProgress(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void initService() {
        // 初始化服务
        musicService = new MusicService();
        // 开启服务需要的Intent
        Intent intent = new Intent(PlayMusicActivity.this, MusicService.class);
        // 开启服务需要的服务链接
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (MusicService.MusicBinder) service;
                // 启动通知线程
                new Thread(new NotifySeekBarRunnable()).start();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        // 绑定服务
        bindService(intent, conn, BIND_AUTO_CREATE);
    }



    /**
     * tablayout和viewpager的设置
     */
    private void addTab() {
        fragments.add(LeftPlayFragment.newInstance());
        fragments.add(MiddlePlayFragment.newInstance());
        fragments.add(RightPlayFragment.newInstance());
        playAdapter.setFragments(fragments);
        viewPager.setAdapter(playAdapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < fragments.size(); i++) {
            tabLayout.getTabAt(i).setCustomView(playAdapter.getTabView(i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_play_before:
                if (binder != null) {

                    binder.lastMusic();
                }
                break;
            case R.id.ac_play_pause:
                if (binder != null) {
                    setMusicInfo();
                    binder.playCurrentMusic();
                }
                break;
            case R.id.ac_play_next:
                if (binder != null) {
                    binder.nextMusic();
                }
                break;
        }
    }



    private void setMusicInfo() {
        if (binder != null) {
            MusicBean bean = binder.getCurrentMusicBean();
            Log.d("PlayMusicActivity", bean.getTitle());
            titleTv.setText(bean.getTitle());
            nameTv.setText(bean.getSinger());
            seekBar.setMax(binder.getCurrentDuration());
        }
    }

    private void syncDuration() {
        if (binder != null) {
            long currentDuration = binder.getCurrentPos();
            long duration = currentDuration;
            duration = duration / 1000;
            String minute = add0(String.valueOf(duration / 60));
            String second = add0(String.valueOf(duration % 60));
            timeTv.setText(minute + ":" + second);
        }
    }

    private String add0(String l) {
        StringBuffer temp = new StringBuffer(l);
        if (temp.length() == 1) {
            temp = new StringBuffer();
            temp.append("0");
            temp.append(l);
        }
        return temp.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.stopMusic();
        unbindService(conn);
    }



    /**
     * 实现高斯模糊
     */
    private Bitmap getBlurBitmap(Bitmap mBitmap) {
        // 找到图片
        // mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.new_song);

        // Init src image
        //test.setImageBitmap(mBitmap);

        // 压缩并保存位图
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f / SCALE_FACTOR, 1.0f / SCALE_FACTOR);
        // 新的压缩位图
        mCompressBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        mCompressBitmap = StackBlur.blurNatively(mCompressBitmap, (int) 1, false);
        return mCompressBitmap;
    }


}
