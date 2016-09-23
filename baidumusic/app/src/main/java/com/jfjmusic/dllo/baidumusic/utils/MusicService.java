package com.jfjmusic.dllo.baidumusic.utils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/23.
 * 播放音乐的后台服务
 */
public class MusicService extends Service{

    private int currentIndex=0;//当前播放的音乐在集合中的索引
    private MyBinder myBinder;//用来传值
    private static List<String> urlDatas=new ArrayList<>();//歌曲链接的集合
    private MediaPlayer mediaPlayer;//音乐播放器
   // private MusicReceiver musicReceiver;
    //定义一个广播接受者,接受从各个界面传来的音乐链接
    public static class MusicReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
                urlDatas=intent.getStringArrayListExtra("name");
                L.d("这是后台服务中得到的数据"+intent.getStringExtra("name"));
        }
    }
    @Override
    public void onCreate() {
  //      musicReceiver=new MusicReceiver();
        L.d("创建播放器服务器");
        //初始化Binder对象
        myBinder=new MyBinder();
        //初始化音乐播放器
        mediaPlayer=new MediaPlayer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
    /**
     * 和服务器传值使用的类
     */
    public class MyBinder extends Binder{
        //获取数据集合的而方法
        public List<String> getUrlDatas(){
            if (urlDatas!=null){
                return urlDatas;
            }else{
                return null;
            }
        }
        //播放
        public void playMp3(){
            try {
                //重置  不写的话带二次播放就甭
                L.d("播放歌曲");
                if (urlDatas!=null) {
                    mediaPlayer.reset();
                    //设置给音乐播放器
                    mediaPlayer.setDataSource(urlDatas.get(currentIndex));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }else{
                    L.d("在播放服务中:播放列表为空");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //暂停
        public void pauseMp3(){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else {
                mediaPlayer.start();
            }
        }
        //获取当前歌曲的url
        public String getCurrentStr(){
            return urlDatas.get(currentIndex);
        }
        //下一曲
        public void nextMp3(){
            currentIndex++;
            if (currentIndex==urlDatas.size()){
                L.d("在服务器中,音乐播放列表到头了,又重来");
                currentIndex=0;
            }
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(urlDatas.get(currentIndex));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //上一曲
        public void pastMp3(){
            currentIndex--;
            if (currentIndex<0){
                currentIndex=urlDatas.size()-1;
            }
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(urlDatas.get(currentIndex));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //获取当前音乐播放的状态
        public boolean getMp3IsPalying(){
            return mediaPlayer.isPlaying();
        }
        //获取音乐时长
        public  int getMp3Duration(){
            return mediaPlayer.getDuration();
        }
        //获取当前音乐播放进度
        public  int getMp3CurrentPostion(){
            return mediaPlayer.getCurrentPosition();
        }
        //快进快退
        public void goGOGO(int postion){
            mediaPlayer.seekTo(postion);
        }
        //停止播放音乐
        public void stopMp3(){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }
}
