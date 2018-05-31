package com.zmm.cniao5playtest.data;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.IndexBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 2017/1/3.
 */

public class AppInfoModel {


    private ApiService mApiService;

    public AppInfoModel(ApiService apiService) {
        mApiService = apiService;
    }

//    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
//
//        // log用拦截器
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//
//        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
////        SSLSocketFactory sslSocketFactory = null;
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
////                .addInterceptor(new HeadInterceptor())
//                .addInterceptor(logging)
//                // 连接超时时间设置
//                .connectTimeout(10, TimeUnit.SECONDS)
//                // 读取超时时间设置
//                .readTimeout(10, TimeUnit.SECONDS)
//
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiService.OSS_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(okHttpClient)
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        return apiService.getApps2();
//    }

    public Observable<BaseBean<IndexBean>> index(){

        return  mApiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return mApiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> games(int page){
        return mApiService.games(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory( int categoryid,  int page){

        return  mApiService.getFeaturedAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory( int categoryid, int page){

        return  mApiService.getTopListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory( int categoryid, int page){

        return  mApiService.getNewListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail( int id){

        return  mApiService.getAppDetail(id);
    }

}
