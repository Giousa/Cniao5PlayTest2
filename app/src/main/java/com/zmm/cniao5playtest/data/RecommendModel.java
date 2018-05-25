package com.zmm.cniao5playtest.data;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.data.http.ApiService;

import rx.Observable;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendModel {


    private ApiService mApiService;

    public RecommendModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){

        return mApiService.getApps("{'page':0}");
    }


}
