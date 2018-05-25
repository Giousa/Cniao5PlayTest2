package com.zmm.cniao5playtest.common.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.zmm.cniao5playtest.common.rx.RxErrorHandler;
import com.zmm.cniao5playtest.common.util.ProgressDialogHandler;
import com.zmm.cniao5playtest.ui.BaseView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/25
 * Time:下午2:25
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T>  implements ProgressDialogHandler.OnProgressCancelListener{


    private ProgressDialogHandler mProgressDialogHandler;


    public ProgressDialogSubscriber(Context context) {
        super(context);

        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }

    protected boolean isShowProgressDialog(){
        return  true;
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

    @Override
    public void onStart() {

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }

    }

    @Override
    public void onCompleted() {

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }
}
