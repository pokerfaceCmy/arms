package com.pokerfacecmy.common.net.func;

import com.pokerfacecmy.common.net.exception.IExceptionHandler;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 18:27
 * @description Rxjava异常处理
 * @email cheng.meng.yuan@qq.com
 */
public class ExceptionHandleFunc<T> implements Function<Throwable, Observable<T>> {
    private IExceptionHandler mExceptionHandler;

    public ExceptionHandleFunc(IExceptionHandler mExceptionHandler) {
        this.mExceptionHandler = mExceptionHandler;
    }

    @Override
    public Observable<T> apply(Throwable throwable) {
        //cast to ApiException 转换为ApiException
        return Observable.error(mExceptionHandler.handleException(throwable));
    }
}
