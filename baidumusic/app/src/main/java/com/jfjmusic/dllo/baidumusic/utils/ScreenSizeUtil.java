package com.jfjmusic.dllo.baidumusic.utils;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.jfjmusic.dllo.baidumusic.controller.app.MyApp;

/**
 * Created by dllo on 16/9/14.
 * 获得屏幕的大小的工具类
 */
public class ScreenSizeUtil {
    /**
     * 加入枚举类型
     */
    public enum ScreenState {
        WIDTH,//屏幕的宽
        HEIGHT//屏幕的高
    }

    public static int getScreenSize(ScreenState state) {
        //获取窗口管理者
        WindowManager manager = (WindowManager) MyApp.getContext().getSystemService(MyApp.getContext().WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //经屏幕的尺寸设置给显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        //返回屏幕宽度
        //ScreenState.WIDTH=metrics.widthPixels;
        //ScreenState.HEIGHT=metrics.heightPixels;
        if (state.equals(ScreenState.HEIGHT)){
            return metrics.heightPixels;
        }else{
            return metrics.widthPixels;
        }
    }

}
