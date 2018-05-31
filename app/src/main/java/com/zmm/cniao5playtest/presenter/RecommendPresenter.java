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


    public void requestDatas() {

        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubcriber<IndexBean>(mContext,mView) {

                    @Override
                    public void onNext(IndexBean indexBean) {

                        mView.showResult(indexBean);
                    }
                });



    }
}
