package com.jfjmusic.dllo.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/10.
 * k歌界面的大家都在唱的listView的实体类
 */
public class KtvAllSingBean {
    private String name;
    private int number;//唱过的人数

    public KtvAllSingBean() {
    }

    public KtvAllSingBean(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
