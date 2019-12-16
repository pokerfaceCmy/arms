package com.pokerfacecmy.common.net.subscriber;

import com.pokerfacecmy.common.mvp.view.IBaseView;
import com.pokerfacecmy.common.net.exception.ApiException;

import io.reactivex.observers.DisposableObserver;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 10:47
 * @description com.pokerfacecmy.common.net.subscriber
 * @email cheng.meng.yuan@qq.com
 */
public abstract class ApiObserver<T> extends DisposableObserver<T> {
    private IBaseView apiAction;
    private boolean isShowLoading = false, isShowMsg = false;

    public ApiObserver() {
        apiAction = null;
    }

    public ApiObserver(IBaseView apiAction) {
        this.apiAction = apiAction;
    }

    public ApiObserver(IBaseView apiAction, boolean isShowLoading, boolean isShowMsg) {
        this.apiAction = apiAction;
        this.isShowLoading = isShowLoading;
        this.isShowMsg = isShowMsg;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isShowLoading) {
            apiAction.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        dismissLoading();
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException && ApiException.DATA_NULL.equals(((ApiException) e).getCode())) {
            dataNull();
            return;
        }
        if (isShowMsg && e instanceof ApiException) {
            apiAction.toast(e.getMessage());
        }
        e.printStackTrace();
        dismissLoading();
    }

    /**
     * 请求成功但是无数据返回
     */
    public void dataNull() {

    }

    @Override
    public void onComplete() {
        dismissLoading();
    }

    /**
     * 请求成功,abstract方法让调用的业务自己去实现
     *
     * @param t 返回的数据
     */
    public abstract void success(T t);

    private void dismissLoading() {
        if (isShowLoading) {
            apiAction.dismissLoading();
        }
    }
}
