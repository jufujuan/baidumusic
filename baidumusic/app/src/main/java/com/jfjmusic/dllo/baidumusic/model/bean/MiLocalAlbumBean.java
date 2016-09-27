package com.jfjmusic.dllo.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/26.
 * 我的--本地音乐--专辑---的实体类
 */
public class MiLocalAlbumBean {
    private int albumImg;
    private String albumTitle;
    private int songCount;

    public MiLocalAlbumBean() {
    }

    public MiLocalAlbumBean(int albumImg, String albumTitle, int songCount) {
        this.albumImg = albumImg;
        this.albumTitle = albumTitle;
        this.songCount = songCount;
    }

    public int getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(int albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }
}
