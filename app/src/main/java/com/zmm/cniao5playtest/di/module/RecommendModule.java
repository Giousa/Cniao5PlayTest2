package com.zmm.cniao5playtest.di.module;

import com.zmm.cniao5playtest.data.RecommendModel;
import com.zmm.cniao5playtest.data.http.ApiService;
import com.zmm.cniao5playtest.presenter.RecommendPresenter;
import com.zmm.cniao5playtest.presenter.contract.RecommendContract;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午3:51
 */

@Module
public class RecommendModule {


    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        mView = view;
    }


    @Provides
    public RecommendContract.View proviceRecommendView(){

        return mView;
    }

    @Provides
    public RecommendModel proviceRecommendModel(ApiService apiService){

        return new RecommendModel(apiService);
    }
}
