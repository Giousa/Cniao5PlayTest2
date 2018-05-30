package com.zmm.cniao5playtest.presenter;


import com.zmm.cniao5playtest.bean.IndexBean;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ProgressSubcriber;
import com.zmm.cniao5playtest.data.AppInfoModel;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel,AppInfoContract.View> {



    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
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

        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubcriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {

                        mView.showResult(indexBean);
                    }
                });

//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
//                    @Override
//                    public Observable<PageBean<AppInfo>>call(Boolean aBoolean) {
//
//                        if(aBoolean){
//
//                            System.out.println("允许权限");
//                            return  mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());
//                        }
//                        else{
//                            System.out.println("拒绝权限");
//                            return Observable.empty();
//                        }
//
//
//                    }
//                })
//                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });


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
