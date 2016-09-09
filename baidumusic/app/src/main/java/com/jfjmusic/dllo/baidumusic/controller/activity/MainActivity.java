package com.jfjmusic.dllo.baidumusic.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.controller.fragment.TestA;

public class MainActivity extends AbsBaseActivity{

    private TextView textView;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;

    }

    @Override
    protected void initViews() {
         textView=byView(R.id.textView);
    }

    @Override
    protected void initDatas() {
         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 goTo(MainActivity.this, TestB.class);
             }
         });
    }
}
