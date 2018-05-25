package com.zmm.cniao5playtest.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.zmm.cniao5playtest.common.exception.BaseException;
import com.zmm.cniao5playtest.common.rx.RxErrorHandler;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/25
 * Time:上午11:48
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {


    protected RxErrorHandler mRxErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context){

        this.mContext = context;


        mRxErrorHandler = new RxErrorHandler(mContext);

    }

    @Override
    public void onError(Throwable e) {


        e.printStackTrace();
        BaseException baseException =  mRxErrorHandler.handlerError(e);

        if(baseException == null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        } else {
            mRxErrorHandler.showErrorMessage(baseException);
        }

    }
}
