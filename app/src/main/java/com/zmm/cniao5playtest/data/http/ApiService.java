package com.zmm.cniao5playtest.data.http;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.bean.requestbean.LoginRequestBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 2016/12/30.
 */

public interface ApiService {


    String OSS_URL = "http://uog.oss-cn-shanghai.aliyuncs.com/data/";

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";




//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

//    @GET("featured")
//    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);


    @GET("recommend3.json")
    Observable<BaseBean<PageBean<AppInfo>>> getApps2();

    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @POST("login")
    Observable<BaseBean> login(@Body LoginRequestBean bean);
}
