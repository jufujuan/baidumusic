package com.jfjmusic.dllo.baidumusic.controller.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.PlayAcViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.LeftPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.MiddlePlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.RightPlayFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.PlayBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.MusicService;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import net.qiujuer.genius.blur.StackBlur;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/28.
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
    private GestureDetector gestureDetector;
    private ImageView finishBtn;
    //用于判断是否正在播放,默认是不播放的
    private Boolean isPlay=false;

    /**
     * 定义音乐播放列表
     */
    private static String currentUrl;

    private ImageView pauseBtn, nextBtn, beforBtn;
    private TextView titleTv, nameTv, timeTv;
    private SeekBar seekBar;

    private StringBuffer buffer;//用于存储音乐接口的数据(进行分离后的数据)
    private String musicUrl = Unique.PLAY_MUSIC_BEFORE + "270852097" + Unique.PLAY_MUSIC_AFTER;
    private static List<String> urlDatas = new ArrayList<>();

    /**
     * 服务相关的声明
     */
    // private MediaPlayer mp;
    private Intent intent;//用来与服务进行通信
    private MusicService.MyBinder musicBinder;//用来得到服务中的各种方法
    //这里是必须写的方法,用来连接服务
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (MusicService.MyBinder) service;
            //绑定服务后,启动刷新进度条线程
//            new Thread(new MpsRunnable()).start();//这个线程死循环监听
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    // 播放器相关
    // 随时通知进度条刷新的Runnable
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    int index = (int) msg.obj;
                    seekBar.setProgress(index);
                    break;
            }
            return false;
        }
    });
    private Bitmap imgBg;
    private PlayBean playBean;
    private PlayBean playBean1;

    //控制进度条的线程
    private class MpsRunnable implements Runnable {
        @Override
        public void run() {
            //如果音乐播放就获取进度
            while (true) {
                if (musicBinder != null) {
                    //如果音乐播放了就获取进度
                    if (musicBinder.getMp3IsPalying()) {
                        int prograss = musicBinder.getMp3CurrentPostion();
                        Message message = new Message();
                        message.what = 100;
                        message.obj = prograss;
                        handler.sendMessage(message);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
//        fragments = new ArrayList<>();
//        playAdapter = new PlayAcViewPagerAdapter(getSupportFragmentManager(), this);
//        viewPager = byView(R.id.ac_play_viewpager);
//        tabLayout = byView(R.id.ac_play_Tablayout);

        root = byView(R.id.ac_play_music_root);
        pauseBtn = byView(R.id.ac_play_pause);
        nextBtn = byView(R.id.ac_play_next);
        beforBtn = byView(R.id.ac_play_before);
        seekBar = byView(R.id.ac_play_seekbar);
        titleTv = byView(R.id.ac_play_song_title);
        nameTv = byView(R.id.ac_play_song_name);
        timeTv = byView(R.id.ac_play_song_all_time);

        finishBtn=byView(R.id.ac_play_music_finish);

        buffer = new StringBuffer();
        //绑定服务
        intent = new Intent(PlayMusicActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        Intent intentSongtList=getIntent();
        if (intentSongtList!=null) {
            //获得从歌单中得到的音乐列表
            List<String> songIds = intentSongtList.getStringArrayListExtra("songidlist");
        }

    }

    @Override
    protected void initDatas() {
        new Thread(new MpsRunnable()).start();
        //添加tablayout等的适配器信息等
       // addTab();
        //单击事件设置监听
        pauseBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        beforBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);

        VolleyInstance.getVolleyInstance().startRequest(musicUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                String newStr = resultStr.substring(1, resultStr.length() - 2);
                playBean1 = new Gson().fromJson(newStr, PlayBean.class);
                currentUrl= playBean1.getBitrate().getFile_link();
                urlDatas.add(currentUrl);
                //设置播放的背景图片
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        L.d("已经进入线程88888888888888888888888888888888888888888");
                        URL fileUrl = null;
                        imgBg = null;

                        try {

                            fileUrl = new URL(playBean1.getSonginfo().getPic_big());
                            L.d("开始获取图片的网址111111111111111111111111111111111"+fileUrl);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            L.d("图片进行获取22222222222222222222222222222222222222");
                            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
                            L.d("图片获取完毕33333333333333333333333333333333");
                            InputStream is = conn.getInputStream();
                            L.d("44444444444444444444444444444444444444444");
                            imgBg = BitmapFactory.decodeStream(is);
                            L.d("555555555555555555555555555555555555555555");
                            // Drawable drawab=new BitmapDrawable(imgBg);
                            // root.setBackground(drawab);
                            conn.disconnect();
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (imgBg!=null) {
                            L.d("播放界面的图片已经获取到!");
                            Bitmap imgBlurBg = getBlurBitmap(imgBg);
                            Drawable drawableBg = new BitmapDrawable(imgBlurBg);
                            root.setBackground(drawableBg);
                        }else{
                            L.d("图片是空的");
                        }
                    }
                }).start();
                L.d("在数据网络解析中"+currentUrl);
                Intent intentMusic = new Intent();
                intentMusic.setAction("com.jfjmusic.dllo.baidumusic.utils.MusicReceiver");
                intentMusic.putStringArrayListExtra("name", (ArrayList<String>) urlDatas);
                sendBroadcast(intentMusic);
            }
            @Override
            public void failure() {

            }
        });

        //实现下滑关闭
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if ((e2.getRawY() - e1.getRawY()) > 200) {
                    finish();
                    return true;
                }
                return false;
            }
        });
        /******************************************/
        //实现快进快退,给进度条状态设置监听事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (musicBinder != null) {
                        //改变音乐进度
                        musicBinder.goGOGO(progress);
                        //进度条改变进度
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 播放上一曲
             */
            case R.id.ac_play_before:
                musicBinder.pastMp3();
                break;
            /**
             * 播放当前歌曲
             */
            case R.id.ac_play_pause:

                if (isPlay){
                    isPlay=false;
                    pauseBtn.setImageResource(R.mipmap.bt_widget_play_press);
                }else {
                    isPlay=true;
                    pauseBtn.setImageResource(R.mipmap.bt_widget_pause_press);
                }
                L.d("在activity中是否有数据"+urlDatas.size());
                if (seekBar.getProgress()==0){
                    musicBinder.playMp3();
                    seekBar.setMax(musicBinder.getMp3Duration());
                }else{
                    musicBinder.pauseMp3();
                }
                break;
            /**
             * 播放下一曲
             */
            case R.id.ac_play_next:
                musicBinder.nextMp3();
                break;
            //点击退出事件
            case R.id.ac_play_music_finish:
                finish();
                Log.d("PlayMusicActivity", "closeeeee");
                break;
        }
    }


    /**
     * tablayout和viewpager的设置
     */
//    private void addTab() {
//        fragments.add(LeftPlayFragment.newInstance());
//        fragments.add(MiddlePlayFragment.newInstance());
//        fragments.add(RightPlayFragment.newInstance());
//        playAdapter.setFragments(fragments);
//        viewPager.setAdapter(playAdapter);
//        viewPager.setCurrentItem(1);
//        tabLayout.setupWithViewPager(viewPager);
//        for (int i = 0; i < fragments.size(); i++) {
//            tabLayout.getTabAt(i).setCustomView(playAdapter.getTabView(i));
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        unbindService(serviceConnection);
        if (musicBinder != null) {
            musicBinder.stopMp3();
        }
//        置空传值对象
        musicBinder = null;
    }
}

