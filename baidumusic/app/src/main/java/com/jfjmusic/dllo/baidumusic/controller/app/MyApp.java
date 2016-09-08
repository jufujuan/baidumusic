package com.jfjmusic.dllo.baidumusic.controller.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/9/8.
 * 当前应用
 * 在这里你能够
 * 1.获取环境context
 */
public class MyApp extends Application{


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    /**
     * 静态方法获取上下文
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
