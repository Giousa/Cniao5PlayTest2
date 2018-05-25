package com.zmm.cniao5playtest.presenter;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.common.rx.RxErrorHandler;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ErrorHandlerSubscriber;
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


    private RxErrorHandler mRxErrorHandler;

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view, RxErrorHandler errorHandler) {
        super(model, view);
        this.mRxErrorHandler = errorHandler;
    }


    public void requestDatas() {




        mModel.getApps()
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<PageBean<AppInfo>>(mRxErrorHandler) {

                    @Override
                    public void onStart() {
                        mView.showLodading();

                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> response) {
                        System.out.println("---onNext---");
                        if(response != null){
                            System.out.println("---onNext---"+response.getDatas());

                            mView.showResult(response.getDatas());
                        } else{
                            mView.showNodata();
                        }

                        mView.dismissLoading();

                    }
                });

//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new Subscriber<PageBean<AppInfo>>() {
//
//                    @Override
//                    public void onStart() {
//                        mView.showLodading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//                        if(response != null){
//
//                            mView.showResult(response.getDatas());
//                        }
//                        else{
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//                    }
//                });


    }
}
