package com.jfjmusic.dllo.baidumusic.utils;

/**
 * Created by dllo on 16/9/12.
 * 常量类
 */
public final class Unique {
    /**
     * 用于替换占位布局的广播的action
     */
    public final static String MAIN_AC_ACTION="com.jfjmusic.dllo.baidumusic.controller.activity";

    /**
     * minefragment发送广播的本地音乐广播发送的类型
     */
    public final static int MINE_LOCAL_MUSIC_TYPE=1;
    /**
     * minefragment发送广播的最近播放广播发送的类型
     */
    public final static int MINE_CURRENT_PLAY_TYPE=2;
    /**
     * 排行榜界面发送广播的最近播放广播发送的类型
     */
    public final static int MUSICL_CHART_PLAY_TYPE=3;

    /**
     * 排行榜界面的网址
     */
    public final static String ML_CHART_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";

    /**
     * 歌单界面的网址
     */
    public final static String ML_SONG_LIST_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";

    /**
     * 电台界面的网址
     */
    public final static String ML_RADIO_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.scene.getCategoryScene&category_id=0";
    /**
     * Mv最新界面的网址
     */
    public final static String ML_MV_NEW_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    /**
     * Mv最热界面的网址
     */
    public final static String ML_MV_HOT_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    /**
     * k歌界面的网址
     */
    public final static String KTV_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.learn.now&page_size=50";
    /**
     * 直播界面上(分类)的网址
     */
    public final static String LIVERADIO_CLASS="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.category";
    /**
     * 直播界面下(人们直播)的网址
     */
    public final static String LIVERADIO_HOT="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";




}
