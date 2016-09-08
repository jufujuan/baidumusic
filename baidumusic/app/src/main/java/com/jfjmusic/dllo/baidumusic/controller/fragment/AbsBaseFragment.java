package com.jfjmusic.dllo.baidumusic.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jfjmusic.dllo.baidumusic.controller.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/9/8.
 * Fragment的基类
 */
public abstract class AbsBaseFragment extends Fragment{

    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化组件
        initViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initDatas();
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
        /**
         * getView()获得fragment的layout
         */
        return (T) getView().findViewById(resId);
    }

    /**
     * 跳转不传值
     */
    protected void goTo(Class<? extends AbsBaseActivity> to) {
        /**
         * startActivity不能再fragment中直接启动
         * 必须使用getActivity()或者context
         */
        context.startActivity(new Intent(context, to));
    }
    /**
     * 跳转传值
     */
    protected void goTo( Class<? extends AbsBaseActivity> to,Bundle bundle) {
        Intent intent=new Intent(context, to);
        intent.putExtras(bundle);
        /**
         * startActivity不能再fragment中直接启动
         * 必须使用getActivity()或者context
         */
        context.startActivity(intent);
    }
}
