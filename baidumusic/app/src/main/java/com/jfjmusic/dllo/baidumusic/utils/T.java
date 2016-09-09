package com.jfjmusic.dllo.baidumusic.utils;

import android.widget.Toast;

import com.jfjmusic.dllo.baidumusic.controller.app.MyApp;

/**
 * Created by dllo on 16/9/8.
 * MyToast将toast进行封装
 * toast的统一管理类
 */
public final class T {

    private static Boolean isDebug = true;


    //无参数的构造方法,私有证明该对象不能够被创建
    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     * @param msg
     */
    protected void showShortMsg(CharSequence msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
    protected void showShortMsg(int msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 长时间显示Toast
     * @param msg
     */
    protected void showLongMsg(CharSequence msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
    protected void showLongMsg(int msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration)
    {
        if (isDebug)
            Toast.makeText(MyApp.getContext(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration)
    {
        if (isDebug)
            Toast.makeText(MyApp.getContext(), message, duration).show();
    }

}
