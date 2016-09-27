package com.jfjmusic.dllo.baidumusic.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.app.MyApp;

/**
 * Created by dllo on 16/9/8.
 * Activity的基类
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayout());

        initViews();

        initDatas();

    }

    protected void addAnimator(){

//        addAnimator();
    }

    @Override
    public void finish() {
        super.finish();

    }

    /**
     * 设置布局
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 简化的findViewById
     * 利用了泛型
     */
    protected <T extends View> T byView(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 跳转不传值
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to) {
        startActivity(new Intent(from, to));
        overridePendingTransition(R.anim.in,R.anim.out);
    }
    /**
     * 跳转传值
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to,Bundle bundle) {
        Intent intent=new Intent(from, to);
        intent.putExtras(bundle);
        startActivity(intent);
        addAnimator();
    }


}


