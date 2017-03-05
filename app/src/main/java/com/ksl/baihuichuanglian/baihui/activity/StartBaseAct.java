package com.ksl.baihuichuanglian.baihui.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.ksl.baihuichuanglian.baihui.application.MyApp;

/**
 * 作者：Bill on 2017/2/17 13:49
 * 备注：
 */
public class StartBaseAct extends Activity {

    /**
     * 自定义的用于findViewById的$方法
     * @param viewID 视图ID
     * @param <T> 泛型
     * @return View
     */
    @SuppressWarnings("unchecked")
    protected <T> T $(int viewID){
        return (T) findViewById(viewID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp)getApplication()).addActivity(this);
        initStatusBar();
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
