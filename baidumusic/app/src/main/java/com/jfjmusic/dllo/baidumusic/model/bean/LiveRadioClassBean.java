package com.jfjmusic.dllo.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/17.
 * 直播界面上方分类的实体类
 */
public class LiveRadioClassBean {

    /**
     * error_code : 22000
     * data : [{"category_id":"hot","category_name":"全部","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_all.png?v=1666534791","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_all_normal.png?v=1156567862","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_all_press.png?v=705291557"},{"category_id":"4","category_name":"女神","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_goddess.png?v=1675118203","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_goddess_normal.png?v=1628793203","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_goddess_press.png?v=2113871069"},{"category_id":"3","category_name":"好声音","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_voice.png?v=542378699","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_voice_normal.png?v=1187401453","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_voice_press.png?v=163008604"},{"category_id":"new","category_name":"新秀","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_rookie.png?v=1135331670","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_rookie_normal.png?v=109462346","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_rookie_press.png?v=2006704194"},{"category_id":"2","category_name":"劲爆","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_madden.png?v=987868","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_madden_normal.png?v=777127850","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_madden_press.png?v=1426477831"},{"category_id":"15","category_name":"搞笑","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_happy.png?v=2022773612","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_happy_normal.png?v=787646232","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_happy_press.png?v=23337596"},{"category_id":"1","category_name":"萌妹","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_sister.png?v=991289796","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_sister_normal.png?v=1631505786","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_sister_press.png?v=1811446550"},{"category_id":"recommend","category_name":"推荐","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_recommend.png?v=575333705","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_recommend_normal.png?v=1827835609","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_recommend_press.png?v=1403474130"}]
     */

    private int error_code;
    /**
     * category_id : hot
     * category_name : 全部
     * img_url : http://img.static.9xiu.com/public/yy_category/ic_live_all.png?v=1666534791
     * img_url_normal : http://img.static.9xiu.com/public/yy_category/ic_live_all_normal.png?v=1156567862
     * img_url_press : http://img.static.9xiu.com/public/yy_category/ic_live_all_press.png?v=705291557
     */

    private List<DataBean> data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String category_id;
        private String category_name;
        private String img_url;
        private String img_url_normal;
        private String img_url_press;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getImg_url_normal() {
            return img_url_normal;
        }

        public void setImg_url_normal(String img_url_normal) {
            this.img_url_normal = img_url_normal;
        }

        public String getImg_url_press() {
            return img_url_press;
        }

        public void setImg_url_press(String img_url_press) {
            this.img_url_press = img_url_press;
        }
    }
}
