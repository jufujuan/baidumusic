package com.jfjmusic.dllo.baidumusic.controller.activity;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.adapter.viewpager.PlayAcViewPagerAdapter;
import com.jfjmusic.dllo.baidumusic.controller.fragment.mine.MICurrentPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.mine.MiDownLoadFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.mine.MiLocalMusicFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.MainFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.musiclibrary.MLChartDatilsFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.LeftPlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.MiddlePlayFragment;
import com.jfjmusic.dllo.baidumusic.controller.fragment.play.RightPlayFragment;
import com.jfjmusic.dllo.baidumusic.model.bean.LocalMusicBean;
import com.jfjmusic.dllo.baidumusic.model.bean.PlayBean;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyInstance;
import com.jfjmusic.dllo.baidumusic.model.net.VolleyResult;
import com.jfjmusic.dllo.baidumusic.utils.HttpDownloader;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.MusicService;
import com.jfjmusic.dllo.baidumusic.utils.ScreenSizeUtil;
import com.jfjmusic.dllo.baidumusic.utils.T;
import com.jfjmusic.dllo.baidumusic.utils.Unique;

import net.qiujuer.genius.blur.StackBlur;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity implements View.OnClickListener {

    //主界面相关内容
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private MainFragment mainFragment;
    private MyBroadReceiver myBroadReceiver;
    private LinearLayout textMiniBarLL;
    //popWindows相关内容
    private View popView;
    private ImageView playMainBtn, pastMainBtn, nextMainBtn;
    private PlayAcViewPagerAdapter playAdapter;
    private TabLayout popTabLayout;
    private ViewPager popViewPager;
    private List<Fragment> fragments;
    private static final int SCALE_FACTOR = 6;
    private ImageView pauseBtn, nextBtn, beforeBtn, finishBtn,modeBtn;
    private TextView titleTv, nameTv, timeTv;
    private SeekBar seekBar;
    private PopupWindow popupWindow;
    //服务相关
    private Intent intent;//用来与服务进行通信
    private MusicService.MyBinder musicBinder;//用来得到服务中的各种方法
    //这里是必须写的方法,用来连接服务
    private ServiceConnection serviceConnection;
    //网络音乐播放的网址拼接
    private String musicUrl = Unique.PLAY_MUSIC_BEFORE + "270852097" + Unique.PLAY_MUSIC_AFTER;
    private PlayBean playBean1;
    private static String currentUrl;
    //音乐播放列表
    private static List<String> urlDatas = new ArrayList<>();
    private Bitmap imgBg;
    //用于判断是否正在播放,默认是不播放的
    private Boolean isPlay = false;
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
    private LinearLayout leftLL,middleLL,rightLL;
    //用来控制播放模式
    private static int playModeCount=Unique.PLAY_MUSIC_MODE_ORDER;
    //popwindows的viewpager中的空间
    private ImageView middleImg;
    private ImageView middleDownLoad;
    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) -120;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) -120;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        //主界面的初始化
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        textMiniBarLL = byView(R.id.ac_main_minibar_text);
        playMainBtn = (ImageView) findViewById(R.id.ac_main_minibar_play);
        pastMainBtn = (ImageView) findViewById(R.id.ac_main_minibar_past);
        nextMainBtn = (ImageView) findViewById(R.id.ac_main_minibar_next);
        //popWindows相关的内容
        popView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_play_music, null);
        popTabLayout = (TabLayout) popView.findViewById(R.id.ac_play_Tablayout);
        popViewPager = (ViewPager) popView.findViewById(R.id.ac_play_viewpager);
        beforeBtn = (ImageView) popView.findViewById(R.id.ac_play_before);
        pauseBtn = (ImageView) popView.findViewById(R.id.ac_play_pause);
        nextBtn = (ImageView) popView.findViewById(R.id.ac_play_next);
        seekBar = (SeekBar) popView.findViewById(R.id.ac_play_seekbar);
        titleTv = (TextView) popView.findViewById(R.id.ac_play_song_title);
        nameTv = (TextView) popView.findViewById(R.id.ac_play_song_name);
        timeTv = (TextView) popView.findViewById(R.id.ac_play_song_all_time);
        finishBtn = (ImageView) popView.findViewById(R.id.ac_play_music_finish);
        modeBtn = (ImageView) popView.findViewById(R.id.ac_play_state_type);
        leftLL= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.fragment_play_left,null);
        middleLL= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.fragment_play_middle,null);
        rightLL= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.fragment_play_right,null);
        middleImg = (ImageView) middleLL.findViewById(R.id.pop_middle_img);
        middleDownLoad= (ImageView) middleLL.findViewById(R.id.middle_download);
    }

    @Override
    protected void initDatas() {
        new Thread(new MpsRunnable()).start();
        initService();
        //动态注册广播
        registerBroad();
        //设置一些默认的信息
        setDefault();
        /**
         * 如果是本地音乐就播放本地音乐列表
         */
        if (false) {
            //网络音乐
            initNetDatas();
        }else{
            //本地音乐
            getMp3Info();
            currentUrl=urlDatas.get(0);
        }
        Intent intentMusic = new Intent();
        intentMusic.setAction("com.jfjmusic.dllo.baidumusic.utils.MusicReceiver");
        intentMusic.putStringArrayListExtra("name", (ArrayList<String>) urlDatas);
        //默认顺序播放模式
        intentMusic.putExtra("mode",Unique.PLAY_MUSIC_MODE_ORDER);
        sendBroadcast(intentMusic);
        //设置组件的监听
        setListener();
    }

    //获取网络数据
    private void initNetDatas() {
        VolleyInstance.getVolleyInstance().startRequest(musicUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                String newStr = resultStr.substring(1, resultStr.length() - 2);
                playBean1 = new Gson().fromJson(newStr, PlayBean.class);
                currentUrl = playBean1.getBitrate().getFile_link();
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
                            L.d("开始获取图片的网址111111111111111111111111111111111" + fileUrl);
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
                        if (imgBg != null) {
                            L.d("播放界面的图片已经获取到!");
                            Bitmap imgBlurBg = getBlurBitmap(imgBg);
                            Drawable drawableBg = new BitmapDrawable(imgBlurBg);
                            // root.setBackground(drawableBg);
                            //popupWindow.setBackgroundDrawable(drawableBg);
                        } else {
                            L.d("图片是空的");
                        }
                    }
                }).start();
                L.d("在数据网络解析中" + currentUrl);
                Intent intentMusic = new Intent();
                intentMusic.setAction("com.jfjmusic.dllo.baidumusic.utils.MusicReceiver");
                intentMusic.putStringArrayListExtra("name", (ArrayList<String>) urlDatas);
                sendBroadcast(intentMusic);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void initService() {
        serviceConnection = new ServiceConnection() {
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
        //绑定服务
        intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    //设置组件的监听事件
    private void setListener() {
        textMiniBarLL.setOnClickListener(this);
        playMainBtn.setOnClickListener(this);
        pastMainBtn.setOnClickListener(this);
        nextMainBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        beforeBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        modeBtn.setOnClickListener(this);
        middleDownLoad.setOnClickListener(this);
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
                //如果播放到结尾了,按钮图片进行更改
                if (progress==seekBar.getMax()){
                    pauseBtn.setImageResource(R.mipmap.bt_widget_play_press);
                    playMainBtn.setImageResource(R.mipmap.bt_minibar_play_normal);
                    musicBinder.stopMp3();
                    musicBinder.nextMp3();
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

    //设置一些默认信息
    private void setDefault() {
        //设置默认替换的占位布局
        mTransaction.replace(R.id.main_framelayout, mainFragment);
        mTransaction.commit();
    }

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

    //监听事件的具体实现
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //底部minibar的点击事件(点击底部弹出播放界面)
            case R.id.ac_main_minibar_text:
                //初始化PopWindows的数据
                initPopWindows();
                //初始化PopWindows并显示
                initPop(v);
                break;
            //底部minibar的上一首
            case R.id.ac_main_minibar_past:
                break;
            //底部minibar的播放按钮
            case R.id.ac_main_minibar_play:
                if (isPlay) {
                    isPlay = false;
                    playMainBtn.setImageResource(R.mipmap.bt_minibar_play_normal);
                    pauseBtn.setImageResource(R.mipmap.bt_widget_play_press);
                } else {
                    isPlay = true;
                    playMainBtn.setImageResource(R.mipmap.bt_minibar_pause_normal);
                    pauseBtn.setImageResource(R.mipmap.bt_widget_pause_press);
                }
                if (seekBar.getProgress() == 0) {
                    musicBinder.playMp3();
                    seekBar.setMax(musicBinder.getMp3Duration());
                } else {
                    musicBinder.pauseMp3();
                }
                break;
            //底部minibar的下一首播放按钮
            case R.id.ac_main_minibar_next:
                musicBinder.nextMp3();
                break;
            //popWindows中的上一曲
            case R.id.ac_play_before:
                musicBinder.pastMp3();
                break;
            //popWindows中的播放按钮
            case R.id.ac_play_pause:
                if (isPlay) {
                    isPlay = false;
                    playMainBtn.setImageResource(R.mipmap.bt_minibar_play_normal);
                    pauseBtn.setImageResource(R.mipmap.bt_widget_play_press);
                } else {
                    isPlay = true;
                    playMainBtn.setImageResource(R.mipmap.bt_minibar_pause_normal);
                    pauseBtn.setImageResource(R.mipmap.bt_widget_pause_press);
                }
                L.d("在activity中是否有数据" + urlDatas.size());
                if (urlDatas.size()>0) {
                    if (seekBar.getProgress() == 0) {
                        musicBinder.playMp3();
                        seekBar.setMax(musicBinder.getMp3Duration());
                    }
                    else {
                        musicBinder.pauseMp3();
                    }
                }else{
                    L.d("播放列表中没有歌曲!");
                }
                break;
            //popWindows中的下一曲
            case R.id.ac_play_next:
                musicBinder.nextMp3();
                break;
            //popWindows中的关闭界面的按钮
            case R.id.ac_play_music_finish:
                popupWindow.dismiss();
                break;
            case R.id.ac_play_state_type:
                playModeCount++;
                Intent intentMusic = new Intent();
                intentMusic.setAction("com.jfjmusic.dllo.baidumusic.utils.MusicReceiver");
                intentMusic.putStringArrayListExtra("name", (ArrayList<String>) urlDatas);
                switch (playModeCount){
                    case Unique.PLAY_MUSIC_MODE_ORDER:
                        T.show("顺序播放",1000);
                        intentMusic.putExtra("mode",Unique.PLAY_MUSIC_MODE_ORDER);
                        modeBtn.setImageResource(R.mipmap.bt_playpage_order_press);
                        break;
                    case Unique.PLAY_MUSIC_MODE_SINGLE_RECYCLER:
                        intentMusic.putExtra("mode",Unique.PLAY_MUSIC_MODE_SINGLE_RECYCLER);
                        modeBtn.setImageResource(R.mipmap.bt_playpage_roundsingle_press);
                        T.show("单曲循环",1000);
                        break;
                    case Unique.PLAY_MUSIC_MODE_ALL_RECYCLER:
                        intentMusic.putExtra("mode",Unique.PLAY_MUSIC_MODE_ALL_RECYCLER);
                        modeBtn.setImageResource(R.mipmap.bt_playpage_loop_press);
                        T.show("循环播放",1000);
                        break;
                    case Unique.PLAY_MUSIC_MODE_RANDOM:
                        intentMusic.putExtra("mode",Unique.PLAY_MUSIC_MODE_RANDOM);
                        modeBtn.setImageResource(R.mipmap.bt_playpage_random_press);
                        T.show("随机播放",1000);
                        playModeCount=0;
                        break;
                }
                sendBroadcast(intentMusic);
                break;
            //音乐下载按钮
            case R.id.middle_download:
                T.show("已加入到歌曲列表",1000);
                downLoadMusic();
                break;
        }
    }
    //下载音乐
    private void downLoadMusic() {
        HttpDownloader http = new HttpDownloader();
        String result = http.download(currentUrl, "/baidumusic", "jfj3"+".mp3");
        L.d("音乐下载的结果:"+result);
    }

    // 初始化PopWindow并显示
    private void initPop(View v) {
        int[] lcoation = new int[2];
        v.getLocationOnScreen(lcoation);
        popupWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT,
                ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) - getStatusBarHeight());
        Bitmap bb = BitmapFactory.decodeResource(getResources(), R.mipmap.scan_first);
        Drawable bg = new BitmapDrawable(getBlurBitmap(bb));
        popupWindow.setBackgroundDrawable(bg);
        popupWindow.setContentView(popView);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(textMiniBarLL, Gravity.NO_GRAVITY, lcoation[0], getStatusBarHeight());

    }

    //初始化PopWindows的数据
    private void initPopWindows() {
        List<LinearLayout> linearLayouts=new ArrayList<>();
        linearLayouts.add(leftLL);
        linearLayouts.add(middleLL);
        linearLayouts.add(rightLL);
        playAdapter=new PlayAcViewPagerAdapter(linearLayouts);
        popViewPager.setAdapter(playAdapter);
        popViewPager.setCurrentItem(1);
        popTabLayout.setupWithViewPager(popViewPager);
        for (int i = 0; i < linearLayouts.size(); i++) {
            popTabLayout.getTabAt(i).setCustomView(R.layout.item_ac_play);
        }
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(height,width);
        //middleImg.setMaxWidth(width);
       // middleImg.setMaxHeight(height);
        middleImg.setLayoutParams(params);
    }

    /**
     * 获取屏幕顶部标题栏高度
     *
     * @return 标题栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //获取本地歌曲信息
    public void getMp3Info(){
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int singerIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int timeIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int urlIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            //判断是否是音乐
            int isMusicIndex = cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC);
            String title = cursor.getString(titleIndex);
            String singer = cursor.getString(singerIndex);
            long duration = cursor.getLong(timeIndex);
            String url = cursor.getString(urlIndex);
            //0不是音乐, 1是音乐
            int isMusic = cursor.getInt(isMusicIndex);
            //处理歌曲
            if (isMusic != 0 && duration / (1000 * 60) >= 1) {
                //把歌曲存入实体类
                LocalMusicBean mp3Bean = new LocalMusicBean();
                mp3Bean.setTitle(title);
                mp3Bean.setSinger(singer);
                mp3Bean.setDuration(duration);
                mp3Bean.setUrl(url);
                //加入集合
                urlDatas.add(mp3Bean.getUrl());
            }
        }
        cursor.close();
    }


    /**
     * 实现高斯模糊
     */
    private Bitmap getBlurBitmap(Bitmap mBitmap) {
        // 压缩并保存位图
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f / SCALE_FACTOR, 1.0f / SCALE_FACTOR);
        // 新的压缩位图
        Bitmap mCompressBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        mCompressBitmap = StackBlur.blurNatively(mCompressBitmap, (int) 4, false);
        return mCompressBitmap;
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
                FragmentTransaction mineTransaction = mFragmentManager.beginTransaction();
                switch (intent.getIntExtra("type", 0)) {
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
                    case  Unique.MINE_DOWNLOAD_TYPE:
                        //下载管理传传过来的广播
                        mineTransaction.addToBackStack(null);
                        mineTransaction.replace(R.id.main_framelayout, MiDownLoadFragment.newInstance());
                        break;
                    case Unique.ML_CHART_DETAILS:
                        //乐库排行榜传过来的广播
                        mineTransaction.addToBackStack(null);
                        mineTransaction.replace(R.id.main_framelayout, MLChartDatilsFragment.newInstance(intent.getStringExtra("urlType")));
                        break;

                }
                mineTransaction.commit();
            }

        }
    }

    // 注册广播
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
        // 注销广播
        unregisterReceiver(myBroadReceiver);
        // 解绑
        unbindService(serviceConnection);
        if (musicBinder != null) {
            musicBinder.stopMp3();
        }
//        置空传值对象
        musicBinder = null;
    }

}
