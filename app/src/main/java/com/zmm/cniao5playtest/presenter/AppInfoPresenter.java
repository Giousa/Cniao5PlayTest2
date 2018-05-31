package com.zmm.cniao5playtest.presenter;

import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.cniao5playtest.common.rx.subscriber.ProgressSubcriber;
import com.zmm.cniao5playtest.data.AppInfoModel;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {



    public static final int  TOP_LIST=1;
    public static final int  GAME=2;
    public static final int  CATEGORY=3;


    public static final int FEATURED=0;
    public static final int TOPLIST=1;
    public static final int NEWLIST=2;



    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }




    public void  request(int type,int page,int categoryId,int flagType){


        Observer subscriber =null;

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
                public void onComplete() {

                    mView.onLoadMoreComplete();
                }

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    mView.showResult(pageBean);
                }
            };

        }


        Observable observable = getObservable(type,page,categoryId,flagType);

        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);


    }





    public void  requestData(int type,int page){

        request(type,page,0,0);

    }


    public void requestCategoryApps(int categoryId,int page,int flagType){


        request(CATEGORY,page,categoryId,flagType);


    }




    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId, int flagType){

        switch (type){

            case TOP_LIST:
                return  mModel.topList(page);


            case GAME:
                return  mModel.games(page);

            case CATEGORY:

                if(flagType==FEATURED){

                    return  mModel.getFeaturedAppsByCategory(categoryId,page);
                }

                else  if(flagType==TOPLIST){

                    return  mModel.getTopListAppsByCategory(categoryId,page);
                }

                else  if(flagType==NEWLIST){

                    return  mModel.getNewListAppsByCategory(categoryId,page);
                }



            default:
                return Observable.empty();
        }

    }


}