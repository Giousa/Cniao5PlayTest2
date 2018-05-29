package com.zmm.cniao5playtest.ui.activity;

import android.Manifest;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.di.component.AppComponent;

import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/29
 * Time:下午3:09
 */

public class LoginActivity extends BaseActivity {

    private RxPermissions mRxPermissions;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

        mRxPermissions = new RxPermissions(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {


        mRxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean){
                            System.out.println("获取权限成功");
                        }else {
                            System.out.println("获取权限失败");
                        }
                    }
                });

    }
}
