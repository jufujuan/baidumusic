package com.jfjmusic.dllo.baidumusic.model.bean;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dllo on 16/9/19.
 * 音乐播放的实体类
 */
public class PlayBean implements Serializable {

    /**
     * error_code : 22000
     * songinfo : {"pic_radio":"http://musicdata.baidu.com/data2/pic/5cde6ee9fa55f49040e6f6c870965f1e/268386312/268386312.jpg","artist_480_800":"","album_id":268386751,"author":"王子文,任贤齐","artist_1000_1000":"","artist_640_1136":"","artist_500_500":"","lrc_md5":"38b9f84e5504e4968d1c77491c9b54c5","artist_id":264865152,"song_id":268386617,"song_title":"时光诛仙","title":"时光诛仙","lrclink":"http://musicdata.baidu.com/data2/lrc/0ac6080e796433986145a16bf9b75696/268817313/268817313.lrc","pic_type":2,"pic_s500":"","album_500_500":"http://musicdata.baidu.com/data2/pic/0012cd97de40d35c50e9e44f2f508997/268386764/268386764.jpg","album_1000_1000":"http://musicdata.baidu.com/data2/pic/aab94b7b5ac0b000baa250a748cca2eb/268386763/268386763.jpg"}
     */

    private int error_code;
    /**
     * pic_radio : http://musicdata.baidu.com/data2/pic/5cde6ee9fa55f49040e6f6c870965f1e/268386312/268386312.jpg
     * artist_480_800 :
     * album_id : 268386751
     * author : 王子文,任贤齐
     * artist_1000_1000 :
     * artist_640_1136 :
     * artist_500_500 :
     * lrc_md5 : 38b9f84e5504e4968d1c77491c9b54c5
     * artist_id : 264865152
     * song_id : 268386617
     * song_title : 时光诛仙
     * title : 时光诛仙
     * lrclink : http://musicdata.baidu.com/data2/lrc/0ac6080e796433986145a16bf9b75696/268817313/268817313.lrc
     * pic_type : 2
     * pic_s500 :
     * album_500_500 : http://musicdata.baidu.com/data2/pic/0012cd97de40d35c50e9e44f2f508997/268386764/268386764.jpg
     * album_1000_1000 : http://musicdata.baidu.com/data2/pic/aab94b7b5ac0b000baa250a748cca2eb/268386763/268386763.jpg
     */

    private SonginfoBean songinfo;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public SonginfoBean getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(SonginfoBean songinfo) {
        this.songinfo = songinfo;
    }

    public static class SonginfoBean {
        private String pic_radio;
        private String artist_480_800;
        private int album_id;
        private String author;
        private String artist_1000_1000;
        private String artist_640_1136;
        private String artist_500_500;
        private String lrc_md5;
        private int artist_id;
        private int song_id;
        private String song_title;
        private String title;
        private String lrclink;
        private int pic_type;
        private String pic_s500;
        private String album_500_500;
        private String album_1000_1000;

        public String getPic_radio() {
            return pic_radio;
        }

        public void setPic_radio(String pic_radio) {
            this.pic_radio = pic_radio;
        }

        public String getArtist_480_800() {
            return artist_480_800;
        }

        public void setArtist_480_800(String artist_480_800) {
            this.artist_480_800 = artist_480_800;
        }

        public int getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(int album_id) {
            this.album_id = album_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getArtist_1000_1000() {
            return artist_1000_1000;
        }

        public void setArtist_1000_1000(String artist_1000_1000) {
            this.artist_1000_1000 = artist_1000_1000;
        }

        public String getArtist_640_1136() {
            return artist_640_1136;
        }

        public void setArtist_640_1136(String artist_640_1136) {
            this.artist_640_1136 = artist_640_1136;
        }

        public String getArtist_500_500() {
            return artist_500_500;
        }

        public void setArtist_500_500(String artist_500_500) {
            this.artist_500_500 = artist_500_500;
        }

        public String getLrc_md5() {
            return lrc_md5;
        }

        public void setLrc_md5(String lrc_md5) {
            this.lrc_md5 = lrc_md5;
        }

        public int getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(int artist_id) {
            this.artist_id = artist_id;
        }

        public int getSong_id() {
            return song_id;
        }

        public void setSong_id(int song_id) {
            this.song_id = song_id;
        }

        public String getSong_title() {
            return song_title;
        }

        public void setSong_title(String song_title) {
            this.song_title = song_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public int getPic_type() {
            return pic_type;
        }

        public void setPic_type(int pic_type) {
            this.pic_type = pic_type;
        }

        public String getPic_s500() {
            return pic_s500;
        }

        public void setPic_s500(String pic_s500) {
            this.pic_s500 = pic_s500;
        }

        public String getAlbum_500_500() {
            return album_500_500;
        }

        public void setAlbum_500_500(String album_500_500) {
            this.album_500_500 = album_500_500;
        }

        public String getAlbum_1000_1000() {
            return album_1000_1000;
        }

        public void setAlbum_1000_1000(String album_1000_1000) {
            this.album_1000_1000 = album_1000_1000;
        }
    }
}
