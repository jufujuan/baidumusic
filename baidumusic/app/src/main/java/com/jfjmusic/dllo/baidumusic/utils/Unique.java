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
     * minefragment发送广播的 本地音乐 广播发送的类型
     */
    public final static int MINE_LOCAL_MUSIC_TYPE=1;
    /**
     * minefragment发送广播的最近播放广播发送的类型
     */
    public final static int MINE_CURRENT_PLAY_TYPE=2;
    /**
     * 排行榜界面发送广播的 最近播放 广播发送的类型
     */
    public final static int MUSICL_CHART_PLAY_TYPE=3;
    /**
     * minefragment发送广播的 下载管理 广播发送的类型
     */
    public final static int MINE_DOWNLOAD_TYPE=4;

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
    /**
     * 推荐界面的网址
     */
    public final static String ML_RECOMMEND="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.plaza.index&cuid=D39E874BD13170332B889C3E2F9F6C0B";
    /**
     * 当前播放音乐的网址
     */
    public final static String PLAY_CURRENT_MUSIC="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.search.lrcpic&format=json&query=%E6%97%B6%E5%85%89%E8%AF%9B%E4%BB%99$$%E7%8E%8B%E5%AD%90%E6%96%87%2C%E4%BB%BB%E8%B4%A4%E9%BD%90&ts=1474290158496&e=ppoCLnl5gToVxgVjQMf63aIVvo2JxG%2Fn1i%2FH5GFszRGRZh0HdnHSzu4QT2Yauzw2SB1EI3xaHUZhZc0S4kj9pw%3D%3D";
    /**
     * 播放音乐的拼接字符串(前)
     */
    public final static String PLAY_MUSIC_BEFORE="http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    /**
     * 播放音乐的拼接字符串(后)
     */
    public final static String PLAY_MUSIC_AFTER="&_=1413017198449";
    /**
     * 播放列表的数据库表名
     */
    private static  final String PLAY_LIST_TABLE_NAME="playlist";
    /**
     * 播放列表的表的列名(播放列表的网址或者本地音乐的路径)
     */
    private static  final String PLAY_LIST_COLUMN_NAME="url";
    /**
     * 播放列表的表的列名(播放列表的网址)
     */
    private static  final String PLAY_LIST_COLUMN_TYPE="type";
    /**
     * 播放列表的id
     */
    private static  final String PLAY_LIST_COLUMN_ID="id";
    /**
     * 音乐列表的播放模式-----顺序播放模式
     */
    public static final int PLAY_MUSIC_MODE_ORDER = 1;
    /**
     * 音乐列表的播放模式-----单曲循环模式
     */
    public static final int PLAY_MUSIC_MODE_SINGLE_RECYCLER = 2;
    /**
     * 音乐列表的播放模式-----循环播放模式
     */
    public static final int PLAY_MUSIC_MODE_ALL_RECYCLER = 3;
    /**
     * 音乐列表的播放模式-----随机播放模式
     */
    public static final int PLAY_MUSIC_MODE_RANDOM = 4;
}
