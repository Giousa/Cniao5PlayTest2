package com.zmm.cniao5playtest.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.zmm.cniao5playtest.common.exception.ApiException;
import com.zmm.cniao5playtest.common.exception.BaseException;
import com.zmm.cniao5playtest.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/25
 * Time:下午1:33
 */

public class RxErrorHandler {


    private Context mContext;

    public RxErrorHandler(Context context) {
        mContext = context;
    }

    public BaseException handlerError(Throwable e){

        BaseException exception = new BaseException();

        if(e instanceof ApiException){

            exception.setCode(((ApiException) e).getCode());

        }else if (e instanceof SocketException){

            exception.setCode(BaseException.SOCKET_ERROR);

        }else if (e instanceof SocketTimeoutException){

            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);

        }else if (e instanceof HttpException){

            exception.setCode(((HttpException) e).code());

        }else{

            exception.setCode(BaseException.UNKNOWN_ERROR);

        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));

        return exception;
    }

    public void  showErrorMessage(BaseException e){


        Toast.makeText(mContext,e.getDisplayMessage(), Toast.LENGTH_LONG).show();

    }
}
