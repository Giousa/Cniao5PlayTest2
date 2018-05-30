package com.zmm.cniao5playtest.presenter;

import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.cniao5playtest.common.rx.subscriber.ProgressSubcriber;
import com.zmm.cniao5playtest.data.AppInfoModel;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/30
 * Time:上午10:47
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {


    public static final int  TOP_LIST=1;
    public static final int  GAME=2;


    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }




    public void  requestData(int type,int page){


        Subscriber subscriber =null;

        if(page==0){

            // 第一页显示loading -----
            subscriber = new  ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {

                    mView.showResult(appInfoPageBean);
                }
            };
        }
        else {

            // 加载下一页
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onCompleted() {

                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    mView.showResult(pageBean);
                }
            };

        }


        Observable observable = getObservable(type,page);

        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);




    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page){

        switch (type){

            case TOP_LIST:
                return  mModel.topList(page);


            case GAME:
                return  mModel.games(page);


            default:
                return Observable.empty();
        }

    }
}
