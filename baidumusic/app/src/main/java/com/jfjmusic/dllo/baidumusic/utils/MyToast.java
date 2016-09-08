package com.jfjmusic.dllo.baidumusic.utils;

import android.widget.Toast;

import com.jfjmusic.dllo.baidumusic.controller.app.MyApp;

/**
 * Created by dllo on 16/9/8.
 * MyToast将toast进行封装
 * 
 */
public final class MyToast {

    private static Boolean isDebug = true;


    //无参数的构造方法,私有证明该对象不能够被创建
    private MyToast() {

    }

    protected void showShortMsg(String msg) {
        if(isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showLongMsg(String msg) {
        if(isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
}
