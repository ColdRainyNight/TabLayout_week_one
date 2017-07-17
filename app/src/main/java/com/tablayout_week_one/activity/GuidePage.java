package com.tablayout_week_one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tablayout_week_one.R;


/**
 * 类描述：引导页 3秒跳转
 * 创建人：xuyaxi
 * 创建时间：2017/7/8 12:11
 */
public class GuidePage extends AppCompatActivity {

    private ImageView guidepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidepage);
        guidepage = (ImageView) findViewById(R.id.guidepage);
        initDemo();
        initHandler();
    }

    private void initDemo() {
        getSupportActionBar().hide();
        //去状态栏 加沉浸式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void initHandler() {
        new Handler(new Handler.Callback() {
            //处理接收到的消息的方法
            @Override
            public boolean handleMessage(Message arg0) {
                //实现页面跳转
                startActivity(new Intent(GuidePage.this, MainActivity.class));
                return false;
            }
        }).sendEmptyMessageDelayed(0, 3000); //表示延时三秒进行任务的执行
    }
}

