package com.zmm.cniao5playtest.common.rx.subscriber;

import com.zmm.cniao5playtest.common.exception.BaseException;
import com.zmm.cniao5playtest.common.rx.RxErrorHandler;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/25
 * Time:上午11:48
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {


    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler rxErrorHandler) {
        mRxErrorHandler = rxErrorHandler;
    }

    @Override
    public void onError(Throwable e) {


        System.out.println("---error---");
        BaseException exception = mRxErrorHandler.handlerError(e);

        mRxErrorHandler.showErrorMessage(exception);

    }
}
