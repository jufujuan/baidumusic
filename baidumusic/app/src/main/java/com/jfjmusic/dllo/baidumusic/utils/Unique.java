package com.jfjmusic.dllo.baidumusic.utils;

/**
 * Created by dllo on 16/9/12.
 * 常量类
 */
public class Unique {
    //用于替换占位布局的广播的action
    public final static String MAIN_AC_ACTION="com.jfjmusic.dllo.baidumusic.controller.activity";

    //minefragment发送广播的本地音乐广播发送的类型
    public final static int MINE_LOCAL_MUSIC_TYPE=1;
    //minefragment发送广播的最近播放广播发送的类型
    public final static int MINE_CURRENT_PLAY_TYPE=2;
    //minefragment发送广播的最近播放广播发送的类型
    public final static int MUSICL_CHART_PLAY_TYPE=3;

    //排行榜界面的网址
    public final static String ML_CHART_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";


    public final static String ML_SONG_LIST_url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";

}
