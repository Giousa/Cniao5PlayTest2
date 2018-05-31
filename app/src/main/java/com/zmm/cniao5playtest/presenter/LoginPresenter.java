package com.zmm.cniao5playtest.presenter;

import android.util.Log;

import com.zmm.cniao5playtest.bean.LoginBean;
import com.zmm.cniao5playtest.common.Constant;
import com.zmm.cniao5playtest.common.rx.RxBus;
import com.zmm.cniao5playtest.common.rx.RxHttpResponseCompat;
import com.zmm.cniao5playtest.common.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.cniao5playtest.common.util.ACache;
import com.zmm.cniao5playtest.common.util.VerificationUtils;
import com.zmm.cniao5playtest.presenter.contract.LoginContract;

import javax.inject.Inject;

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

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {



    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }



    public void login(String phone,String pwd){


        Log.d("LoginPresenter","phone="+phone);
        Log.d("LoginPresenter","pwd="+pwd);

       if(!VerificationUtils.matcherPhoneNum(phone)){

           mView.checkPhoneError();
           return;
       }else {
           mView.checkPhoneSuccess();
       }


        mModel.login(phone,pwd)
                .compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                        mView.showLoading();
                    }

                    @Override
                    public void onComplete() {

                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);

                        //登录成功，发送消息
//                        RxBus.get().post(loginBean.getUser());
                        RxBus.getDefault().post(loginBean.getUser());
                    }
                });



    }


    private void saveUser(LoginBean bean){

        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TOKEN,bean.getToken());
        aCache.put(Constant.USER,bean.getUser());
    }

}
