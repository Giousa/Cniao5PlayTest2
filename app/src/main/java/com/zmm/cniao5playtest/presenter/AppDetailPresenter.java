package com.zmm.cniao5playtest.presenter;


import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ProgressSubcriber;
import com.zmm.cniao5playtest.data.AppInfoModel;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppDetailView> {

    @Inject
    public AppDetailPresenter(AppInfoModel appInfoModel, AppInfoContract.AppDetailView appDetailView) {
        super(appInfoModel, appDetailView);
    }



    public void getAppDetail(int id){


        mModel.getAppDetail(id)
                .compose(RxHttpResponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubcriber<AppInfo>(mContext,mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {

                        mView.showAppDetail(appInfo);
                    }
                });
    }



}
