package com.ksl.baihuichuanglian.baihui.fragmemts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ksl.baihuichuanglian.baihui.R;
import com.ksl.baihuichuanglian.baihui.adapters.MyAdapter;
import com.ksl.baihuichuanglian.baihui.entity.resulte.AdInfo;
import com.ksl.baihuichuanglian.baihui.entity.resulte.CustomInfo;
import com.ksl.baihuichuanglian.baihui.entity.resulte.DataResult;
import com.ksl.baihuichuanglian.baihui.entity.resulte.RetrofitEntity;
import com.ksl.baihuichuanglian.baihui.entity.resulte.StoreInfo;
import com.ksl.baihuichuanglian.baihui.entity.resulte.SubjectStore;
import com.ksl.baihuichuanglian.baihui.httpservice.HttpPostService;
import com.ksl.baihuichuanglian.baihui.interfaces.OnRecyclerItemClickLitener;
import com.ksl.baihuichuanglian.baihui.views.NetworkImageHolderView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Bill on 2017/2/17 14:49
 * 备注：
 */
public class HomePageFragment_2 extends Fragment implements OnRecyclerItemClickLitener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private ImageView btnScan, btnLocation;
    private TextView btnCategory1, btnCategory2, btnCategory3, btnCategory4, btnCategory5, btnCategory6, btnCategory7, btnCategory8;
    private TextView btnBrowseNum, btnClosest, btnBestSell;
    private List<Integer> localImages = new ArrayList<>();
    private List<String> networkImages;   //存放广告图片地址

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    private ImageView ivCustom1, ivCustom2, ivCustom3, ivCustom4;
    private List<CustomInfo> customList = new ArrayList<>();
    private SubjectStore subjectStore;

    private Activity mActivity;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_homepage_2, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.id_recyclerview_2);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //初始化图片加载器（如果只是加载本地图片可以不用这个）
        initImageLoader();

//        restrictHeight(rootView);
        initRecyclerView(inflater, container);

        getData();

        return rootView;
    }


    /**
     * Retrofit加入rxjava实现http请求，获取数据
     * 正常不封装使用
     */
    private void getData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("area_id", "450107");
        map1.put("lon", "22.840636");
        map1.put("lat", "108.320033");
        map1.put("app_version", "1.3");
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("page", 1);
        map2.put("size", 10);
        map2.put("order_by", 1);
        map2.put("client_type", 1);
        map2.put("unique_code", 1);

        String BASE_URL = "http://userapi.dmhlwsc.com/";
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

//        加载框
        final ProgressDialog pd = new ProgressDialog(mActivity);

        HttpPostService apiService = retrofit.create(HttpPostService.class);
        Observable<RetrofitEntity> observable = apiService.getAllVedioBy2(map1, map2);
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<RetrofitEntity>() {
                            @Override
                            public void onCompleted() {
                                if (pd != null && pd.isShowing()) {
                                    pd.dismiss();
                                }
//                                Log.e("aaaaa", "完成");

                                //广告轮播加载网络图片
                                loadNetworkImg(networkImages);


                                ImageLoader loader = ImageLoader.getInstance();
                                loader.displayImage(customList.get(0).getImg(), ivCustom1);
                                loader.displayImage(customList.get(1).getImg(), ivCustom2);
                                loader.displayImage(customList.get(2).getImg(), ivCustom3);
                                loader.displayImage(customList.get(3).getImg(), ivCustom4);


                                myAdapter.setDatas(subjectStore);

                                recyclerView.setAdapter(mHeaderAndFooterWrapper);
                                mHeaderAndFooterWrapper.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (pd != null && pd.isShowing()) {
                                    pd.dismiss();
                                }
                                Log.e("aaaaa", "失败：" + e.getMessage());
                            }

                            @Override
                            public void onNext(RetrofitEntity retrofitEntity) {
                                Log.e("aaaaa", "结果："+ retrofitEntity.getData().getHome_img_ad_list().get(1).getImg());
                                Log.e("aaaaa", "结果："+ retrofitEntity.getData().toString());
                                if (retrofitEntity.getCode() == 201) {
                                    DataResult data = retrofitEntity.getData();

                                    //custom_model的
                                    customList = data.getCustom_model();

                                    //广告的
                                    List<AdInfo> adInfos = data.getHome_img_ad_list();
                                    networkImages = new ArrayList<>();
                                    for (int i = 0; i < adInfos.size(); i++) {
                                        networkImages.add(adInfos.get(i).getImg());
                                    }

                                    //得到的所有店铺信息（包含数量页码）
                                    subjectStore = data.getStore_list_model();
                                }

                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                pd.show();
                            }
                        }

                );
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void findHeadderView(View view) {
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);

        btnScan = (ImageView) view.findViewById(R.id.iv_index_scan);
        btnScan.setOnClickListener(this);
        btnLocation = (ImageView) view.findViewById(R.id.iv_index_location);
        btnLocation.setOnClickListener(this);

        btnCategory1 = (TextView) view.findViewById(R.id.tv_category_1);
        btnCategory2 = (TextView) view.findViewById(R.id.tv_category_2);
        btnCategory3 = (TextView) view.findViewById(R.id.tv_category_3);
        btnCategory4 = (TextView) view.findViewById(R.id.tv_category_4);
        btnCategory5 = (TextView) view.findViewById(R.id.tv_category_5);
        btnCategory6 = (TextView) view.findViewById(R.id.tv_category_6);
        btnCategory7 = (TextView) view.findViewById(R.id.tv_category_7);
        btnCategory8 = (TextView) view.findViewById(R.id.tv_category_8);
        btnCategory1.setOnClickListener(this);
        btnCategory2.setOnClickListener(this);
        btnCategory3.setOnClickListener(this);
        btnCategory4.setOnClickListener(this);
        btnCategory5.setOnClickListener(this);
        btnCategory6.setOnClickListener(this);
        btnCategory7.setOnClickListener(this);
        btnCategory8.setOnClickListener(this);


        ivCustom1 = (ImageView) view.findViewById(R.id.iv_custom1);
        ivCustom2 = (ImageView) view.findViewById(R.id.iv_custom2);
        ivCustom3 = (ImageView) view.findViewById(R.id.iv_custom3);
        ivCustom4 = (ImageView) view.findViewById(R.id.iv_custom4);
        ivCustom1.setOnClickListener(this);
        ivCustom2.setOnClickListener(this);
        ivCustom3.setOnClickListener(this);
        ivCustom4.setOnClickListener(this);

        btnBrowseNum = (TextView) view.findViewById(R.id.tv_browse_number);
        btnClosest = (TextView) view.findViewById(R.id.tv_the_closest);
        btnBestSell = (TextView) view.findViewById(R.id.tv_best_selling);
        btnBrowseNum.setOnClickListener(this);
        btnClosest.setOnClickListener(this);
        btnBestSell.setOnClickListener(this);

    }


    /**
     * 广告轮播加载本地图片
     * @param localImages
     */
    /*private void loadLocalImg(List<Integer> localImages) {
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
//        convenientBanner.setManualPageable(false);//设置不能手动影响
    }*/

    /**
     * 广告轮播加载网络图片
     *
     * @param networkImages
     */
    private void loadNetworkImg(List<String> networkImages) {
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(mActivity, "广告：" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});
        //Tips：如果想设置PageIndicator圆点的间距，可以下载源码当做Module引用到项目修改源码
    }

    /**
     * 初始化网络图片缓存库
     */
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mActivity.getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 广告轮播加载本地图片 初始化数据
     */
    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 广告开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    // 广告停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }


    /**
     * 限制高度
     *
     * @param view
     */
    private void restrictHeight(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int dialogMaxHeight = getResources().getDimensionPixelSize(R.dimen.dialog_max_height);
                if (view.getHeight() > dialogMaxHeight) {
                    ViewGroup.LayoutParams params = view.getLayoutParams();
                    params.height = dialogMaxHeight;
                    view.setLayoutParams(params);
                }
                return true;
            }
        });
    }


    private ImageLoader loader;

    private void initRecyclerView(LayoutInflater inflater, ViewGroup container) {
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView, LinearLayoutManager.VERTICAL, false);
//        layoutManager.setScrollEnabled(false);   //禁止了RecyclerView的滚动，解决滑动出现的卡顿

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loader = ImageLoader.getInstance();

        myAdapter = new MyAdapter(getActivity(), loader);
        myAdapter.setOnRecyclerItemClickLitener(this);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(myAdapter);

        View headerLayout = inflater.inflate(R.layout.home_recy_header, container, false);
        findHeadderView(headerLayout);
        mHeaderAndFooterWrapper.addHeaderView(headerLayout);    //只添加一个太复杂的头布局有点卡顿，拆分成几个头布局添加进去可能会好点

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "点击了item：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }


    /**
     * 根据销售数量由大到小排序
     *
     * @param list
     * @param flag
     * @return
     */
    private List<StoreInfo> sortStoreData3(List<StoreInfo> list, final int flag) {
        Collections.sort(list, new Comparator<StoreInfo>() {
            /*
             * int compare(StoreInfo o1, StoreInfo o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(StoreInfo o1, StoreInfo o2) {
                double dou1 = Double.parseDouble(o1.getSales());
                double dou2 = Double.parseDouble(o2.getSales());
                if (dou1 < dou2) {
                    return 1;
                }
                if (dou1 == dou2) {
                    return 0;
                }
                return -1;
            }
        });
        return list;
    }

    /**
     * 根据距离由近到远排序
     *
     * @param list
     * @param flag
     * @return
     */
    private List<StoreInfo> sortStoreData2(List<StoreInfo> list, final int flag) {
        Collections.sort(list, new Comparator<StoreInfo>() {

            /*
             * int compare(StoreInfo o1, StoreInfo o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(StoreInfo o1, StoreInfo o2) {
                if (o1.getDistance() > o2.getDistance()) {
                    return 1;
                }
                if (o1.getDistance() == o2.getDistance()) {
                    return 0;
                }
                return -1;
            }
        });
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_index_scan:
                Toast.makeText(mActivity, "跳转到扫码页面", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_index_location:
                Toast.makeText(mActivity, "定位", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_category_1:
                Toast.makeText(mActivity, "服装", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_2:
                Toast.makeText(mActivity, "超市", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_3:
                Toast.makeText(mActivity, "充值", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_4:
                Toast.makeText(mActivity, "旅游", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_5:
                Toast.makeText(mActivity, "美食", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_6:
                Toast.makeText(mActivity, "娱乐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_7:
                Toast.makeText(mActivity, "酒店", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_category_8:
                Toast.makeText(mActivity, "丽人", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_custom1:
                Toast.makeText(mActivity, "Custom_1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_custom2:
                Toast.makeText(mActivity, "Custom_2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_custom3:
                Toast.makeText(mActivity, "Custom_3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_custom4:
                Toast.makeText(mActivity, "Custom_4", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_browse_number:
                Toast.makeText(mActivity, "浏览次数", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_the_closest:    //距离最近
//                subjectStore.setList(sortStoreData2(subjectStore.getList(), 1));
//                adapter.setDatas(subjectStore);
                break;

            case R.id.tv_best_selling:  //销售数量
//                subjectStore.setList(sortStoreData3(subjectStore.getList(), 1));
//                adapter.setDatas(subjectStore);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
