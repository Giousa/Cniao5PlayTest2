package com.zmm.cniao5playtest.presenter;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.data.RecommendModel;
import com.zmm.cniao5playtest.presenter.contract.RecommendContract;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {



    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {




        mModel.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PageBean<AppInfo>>() {

            @Override
            public void onStart() {
                mView.showLodading();
            }

            @Override
            public void onCompleted() {
                mView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }

            @Override
            public void onNext(PageBean<AppInfo> response) {
                if(response != null){

                    mView.showResult(response.getDatas());
                }
                else{
                    mView.showNodata();
                }

                mView.dismissLoading();
            }
        });

//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//
//                if(response !=null){
//
//                    mView.showResult(response.body().getDatas());
//                }
//                else{
//                    mView.showNodata();
//                }
//
//                mView.dismissLoading();
//
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//
//            }
//        });

    }
}
