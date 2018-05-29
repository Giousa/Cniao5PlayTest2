package com.zmm.cniao5playtest.presenter;


import android.Manifest;
import android.app.Activity;
import android.support.v4.app.Fragment;

import com.tbruyelle.rxpermissions.RxPermissions;
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

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {



    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

//    public void requestPermission(){
//
//
//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if(aBoolean){
//
//                    mView.onRequestPermissonSuccess();
//                }
//                else{
//
//                    mView.onRequestPermissonError();
//                }
//            }
//        });
//
//    }

    public void requestDatas() {

        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);

        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
                    @Override
                    public Observable<PageBean<AppInfo>>call(Boolean aBoolean) {

                        if(aBoolean){

                            System.out.println("允许权限");
                            return  mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());
                        }
                        else{
                            System.out.println("拒绝权限");
                            return Observable.empty();
                        }


                    }
                })
                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });


//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
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
