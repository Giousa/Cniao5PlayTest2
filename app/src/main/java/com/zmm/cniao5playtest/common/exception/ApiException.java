package com.zmm.cniao5playtest.common.exception;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/25
 * Time:上午10:30
 */

public class ApiException extends BaseException {


    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
