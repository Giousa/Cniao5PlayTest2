package com.zmm.cniao5playtest.presenter;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.common.rx.RxErrorHandler;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.cniao5playtest.common.rx.subscriber.ProgressDialogSubscriber;
import com.zmm.cniao5playtest.common.rx.subscriber.ProgressSubcriber;
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
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> response) {
                        if(response != null && response.getDatas() != null){
                            mView.showResult(response.getDatas());
                        } else{
                            mView.showNodata();
                        }
                    }

                });

//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//                        if(response != null && response.getDatas() != null){
//                            mView.showResult(response.getDatas());
//                        } else{
//                            mView.showNodata();
//                        }
//                    }
//
//                });
//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ErrorHandlerSubscriber<PageBean<AppInfo>>(mRxErrorHandler) {
//
//                    @Override
//                    public void onStart() {
//                        mView.showLodading();
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//                        if(response != null){
//
//                            mView.showResult(response.getDatas());
//                        } else{
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//
//                    }
//                });



    }
}
