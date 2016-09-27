package com.jfjmusic.dllo.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/20.
 * 本地音乐的实体
 */
public class LocalMusicBean {
    private String singer;
    private String title;
    private long duration;
    private String url;

    public LocalMusicBean() {
    }


    public LocalMusicBean(String singer, String title, long duration, String url) {
        this.singer = singer;
        this.title = title;
        this.duration = duration;
        this.url = url;

    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

