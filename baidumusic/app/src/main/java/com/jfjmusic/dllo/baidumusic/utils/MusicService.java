package com.jfjmusic.dllo.baidumusic.utils;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jfjmusic.dllo.baidumusic.controller.app.MyApp;
import com.jfjmusic.dllo.baidumusic.model.bean.MusicBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class MusicService extends Service {

    private List<MusicBean> list;
    private MusicBinder binder;
    // 音乐播放器
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int currentPos = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MusicBinder();
        initMediaPlayer();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private void initMediaPlayer() {
        // 初始化播放列表
        list = queryMusics();
    }

    public class MusicBinder extends Binder {
        public void setMusicList(List<MusicBean> list) {
            MusicService.this.list = list;
        }

        public List<MusicBean> getMusicList() {
            return MusicService.this.list;
        }

        // 播放列表当前位置的音乐
        public void playCurrentMusic() {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(list.get(currentPos).getUrl());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 获取当前音乐的实体
        public MusicBean getCurrentMusicBean(){
            return list.get(currentPos);
        }

        public void pauseMusic() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }

        public void nextMusic() {
            currentPos++;
            if (currentPos == list.size()) {
                currentPos = 0;
            }
            playCurrentMusic();
        }

        public void lastMusic() {
            currentPos--;
            if (currentPos < 0) {
                currentPos = list.size() - 1;
            }
            playCurrentMusic();
        }

        public void stopMusic() {
            // 退出应用
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        // 获得当前播放器的状态
        public boolean getPlayerState() {
            return mediaPlayer.isPlaying();
        }

        // 获取当前歌曲的进度
        public int getCurrentPos() {
            return mediaPlayer.getCurrentPosition();
        }
        // 设置当前歌曲的进度
        public void setCurrentPos(int pos) {
            mediaPlayer.seekTo(pos);
        }
        // 获取当前歌曲长度
        public int getCurrentDuration(){
            return mediaPlayer.getDuration();
        }


    }

    public List<MusicBean> queryMusics() {
        List<MusicBean> list = new ArrayList<>();
        Cursor cursor = MyApp.getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            int isMusicStr = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            // 如果搜索到的音频文件是音乐才添加到数据中
            if (isMusicStr == 1) {
                MusicBean bean = new MusicBean(singer, title, duration, url);
                list.add(bean);
                Log.d("MusicService", title);
                Log.d("MusicService", singer);
                Log.d("MusicService", "duration:" + duration);
                Log.d("MusicService", url);
                Log.d("MusicService", "isMusicStr:" + isMusicStr);
            }


        }
        Log.d("MusicService", "cursor.getCount():" + cursor.getCount());

        cursor.close();
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.stopMusic();
        binder = null;
    }
}

