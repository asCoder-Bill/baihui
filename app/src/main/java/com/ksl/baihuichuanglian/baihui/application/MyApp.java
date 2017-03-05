package com.ksl.baihuichuanglian.baihui.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

import java.util.LinkedList;
import java.util.List;

/**
 * 作者：Bill on 2017/2/17 13:24
 * 备注：
 */
public class MyApp extends Application {

    private static final String TAG = "MyApp";
    private List<Activity> actLists = new LinkedList<>();     //保存打开的Activity， 退出应用finish

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();

        RxRetrofitApp.init(this);
    }

    /**
     * 添加activity
     *
     * @param act
     */
    public void addActivity(Activity act) {
        actLists.add(act);
    }

    public void clearActivity() {
        for (Activity act : actLists) {
            if (act != null) {
                act.finish();
            }
        }
    }

    public void exitApp(){
        clearActivity();
        System.exit(0);
    }
}
