package com.ksl.baihuichuanglian.baihui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ksl.baihuichuanglian.baihui.R;
import com.ksl.baihuichuanglian.baihui.application.MyApp;
import com.ksl.baihuichuanglian.baihui.fragmemts.HomePageFragment;
import com.ksl.baihuichuanglian.baihui.fragmemts.HomePageFragment_2;
import com.ksl.baihuichuanglian.baihui.fragmemts.MineFragment;
import com.ksl.baihuichuanglian.baihui.fragmemts.ServiceFragment;
import com.ksl.baihuichuanglian.baihui.fragmemts.StoreFragment;
import com.ksl.baihuichuanglian.baihui.fragmemts.CartFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

public class MainActivity extends RxFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private long mExitFirstTime;  //用于暂存第一次按返回键的时间
    private static final long INTERVAL = 2000;  //按两次返回键退出间隔的时间
    int[] fragmentId = new int[]{R.id.radio_btn_homepage, R.id.radio_btn_store, R.id.radio_btn_cart, R.id.radio_btn_service, R.id.radio_btn_mine};

    private HomePageFragment homePageFragment;
    private HomePageFragment_2 homePageFragment_2;
    private StoreFragment storeFragment;
    private CartFragment cartFragment;
    private ServiceFragment serviceFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    private View containerView;    //fragment的容器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) getApplication()).addActivity(this);

        initStatusBar();
        fragmentManager = getSupportFragmentManager();
        initRadioButton();  //初始化RadioButton
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

    /**
     * 初始化RadioButton
     */
    private void initRadioButton() {
        RadioGroup tab_group = (RadioGroup) findViewById(R.id.radio_tab);
        tab_group.setOnCheckedChangeListener(this);
        tab_group.check(fragmentId[0]);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }
        if (storeFragment != null) {
            transaction.hide(storeFragment);
        }
        if (cartFragment != null) {
            transaction.hide(cartFragment);
        }
        if (serviceFragment != null) {
            transaction.hide(serviceFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
// 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (checkedId) {
            case R.id.radio_btn_homepage:
                /*if (homePageFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fragment_container, homePageFragment);
                } else {
                    // 如果DeviceFragment不为空，则直接将它显示出来
                    transaction.show(homePageFragment);
                }*/

                if (homePageFragment_2 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    homePageFragment_2 = new HomePageFragment_2();
                    transaction.add(R.id.fragment_container, homePageFragment_2);
                } else {
                    // 如果DeviceFragment不为空，则直接将它显示出来
                    transaction.show(homePageFragment_2);
                }
                break;

            case R.id.radio_btn_store:
                if (storeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    storeFragment = new StoreFragment();
                    transaction.add(R.id.fragment_container, storeFragment);
                } else {
                    // 如果GroupFragment不为空，则直接将它显示出来
                    transaction.show(storeFragment);
                }
                break;

            case R.id.radio_btn_cart:
                if (cartFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    cartFragment = new CartFragment();
                    transaction.add(R.id.fragment_container, cartFragment);
                } else {
                    // 如果DeviceFragment不为空，则直接将它显示出来
                    transaction.show(cartFragment);
                }
                break;

            case R.id.radio_btn_service:
                if (serviceFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    serviceFragment = new ServiceFragment();
                    transaction.add(R.id.fragment_container, serviceFragment);
                } else {
                    // 如果TaskFragment不为空，则直接将它显示出来
                    transaction.show(serviceFragment);
                }
                break;

            case R.id.radio_btn_mine:
                if (mineFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mineFragment = new MineFragment();
                    transaction.add(R.id.fragment_container, mineFragment);
                } else {
                    // 如果TaskFragment不为空，则直接将它显示出来
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();   //记得提交
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitFirstTime) > INTERVAL) {
                Toast.makeText(this, R.string.text_point_out_once_again, Toast.LENGTH_SHORT).show();
                mExitFirstTime = System.currentTimeMillis();
            } else {
                ((MyApp) getApplication()).exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
