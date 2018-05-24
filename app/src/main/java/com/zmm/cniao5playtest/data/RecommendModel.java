package com.zmm.cniao5playtest.data;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.data.http.ApiService;
import com.zmm.cniao5playtest.data.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendModel {


    private ApiService mApiService;

    public RecommendModel(ApiService apiService) {
        mApiService = apiService;
    }

    public void getApps(Callback<PageBean<AppInfo>> callback){



//        HttpManager manager = new HttpManager();
//
//        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);


        mApiService.getApps("{'page':0}").enqueue(callback);

    }


}
