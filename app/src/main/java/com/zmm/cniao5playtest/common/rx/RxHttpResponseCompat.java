package com.zmm.cniao5playtest.common.rx;

import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/25
 * Time:上午9:55
 */

public class RxHttpResponseCompat {


    public static <T>ObservableTransformer<BaseBean<T>,T> compatResult(){

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final BaseBean<T> tBaseBean) throws Exception {

                        if(tBaseBean.success()){
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onComplete();
                                    }catch (Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }else {
                            return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //Rxjav 1.x版本转换方式
//    public static <T> Observable.Transformer<BaseBean<T>,T> compatResult2(){
//
//        return new Observable.Transformer<BaseBean<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
//
//                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(final BaseBean<T> tBaseBean) {
//
//                        if(tBaseBean.success()){
//
//                            return Observable.create(new Observable.OnSubscribe<T>() {
//                                @Override
//                                public void call(Subscriber<? super T> subscriber) {
//
//                                    try {
//                                        subscriber.onNext(tBaseBean.getData());
//                                        subscriber.onCompleted();
//                                    }catch (Exception e){
//                                        subscriber.onError(e);
//                                    }
//
//                                }
//                            });
//
//                        }else {
//                            return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
//                        }
//
//                    }
//                }).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

}
