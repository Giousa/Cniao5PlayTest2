package com.zmm.cniao5playtest.presenter.contract;

import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.LoginBean;
import com.zmm.cniao5playtest.ui.BaseView;

import io.reactivex.Observable;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter.contract
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public interface LoginContract {


    interface  ILoginModel{
        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }


    interface  LoginView extends BaseView {


        void checkPhoneError();
        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
//        void loginError(String msg);

    }
}
