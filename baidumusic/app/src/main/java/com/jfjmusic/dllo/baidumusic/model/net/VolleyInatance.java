package com.jfjmusic.dllo.baidumusic.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jfjmusic.dllo.baidumusic.controller.app.MyApp;

/**
 * Created by dllo on 16/9/9.
 * Volley使用单例
 */
public class VolleyInatance {
    /**
     * 双重校验法写单例
     */

    private static VolleyInatance volleyInatance;

    private RequestQueue queue;

    //私有化构造方法,外部不能随意的创建对象
    private VolleyInatance(){
       queue= Volley.newRequestQueue(MyApp.getContext());
    }
    //对外提供获取对象的静态方法
    public static VolleyInatance getVolleyInatance(){
        if(volleyInatance==null){
            synchronized (VolleyInatance.class){
                if (volleyInatance==null){
                    volleyInatance=new VolleyInatance();
                }
            }
        }
        return volleyInatance;
    }

    //对外提供请求方法
    public void startRequest(String url, final VolleyResult result){
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.failure();
            }
        });
        queue.add(stringRequest);
    }
}
