package com.ksl.baihuichuanglian.baihui.httpservice;


import com.ksl.baihuichuanglian.baihui.entity.resulte.RetrofitEntity;
import com.ksl.baihuichuanglian.baihui.entity.resulte.SubjectResulte;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseResultEntity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 接口service-post相关
 */

public interface HttpPostService {

    @FormUrlEncoded
    @POST("shop/home")
    Observable<RetrofitEntity> getAllVedioBy2(@FieldMap Map<String, String> strMap, @FieldMap Map<String, Integer> intMap);


    @GET("AppFiftyToneGraph/videoLink/{once_no}")
    Observable<BaseResultEntity<List<SubjectResulte>>> getAllVedioBys(@Query("once_no") boolean once_no);

}
