package com.zmm.cniao5playtest.presenter;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.data.RecommendModel;
import com.zmm.cniao5playtest.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {



    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {


        mView.showLodading();

        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {

                if(response !=null){

                    mView.showResult(response.body().getDatas());
                }
                else{
                    mView.showNodata();
                }

                mView.dimissLoading();

            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

                mView.dimissLoading();
                mView.showError(t.getMessage());

            }
        });
    }
}
