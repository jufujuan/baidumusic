package com.jfjmusic.dllo.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库排行中ListView的item的实体类
 */
public class MLChartBean {
    private int img;//榜单的图片
    private String title;//xx榜
    private List<String> songName;

    public MLChartBean() {
    }

    public MLChartBean(int img, String title, List<String> songName) {
        this.img = img;
        this.title = title;
        this.songName = songName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSongName() {
        return songName;
    }

    public void setSongName(List<String> songName) {
        this.songName = songName;
    }
}
