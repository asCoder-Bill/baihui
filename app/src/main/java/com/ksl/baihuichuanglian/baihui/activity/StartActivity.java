package com.ksl.baihuichuanglian.baihui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ksl.baihuichuanglian.baihui.R;

/**
 * 作者：Bill on 2017/2/17 14:01
 * 备注：（APP启动）进入页面
 */
public class StartActivity extends StartBaseAct {

    private static final String VERSION_KEY = "appVersion";   //存储在SharedPreferences中的APP版本的键名
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        handler.postDelayed(skipRunnable, 800);
    }

    Runnable skipRunnable = new Runnable() {
        @Override
        public void run() {
            pageSkip();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(skipRunnable);
        }
    }

    /**
     * 页面跳转
     */
    private void pageSkip() {
        Intent intent = new Intent();
        intent.setClass(StartActivity.this, MainActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.activity_open, 0);
        finish();
    }

}
