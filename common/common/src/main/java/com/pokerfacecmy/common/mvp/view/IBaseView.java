package com.pokerfacecmy.common.mvp.view;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 9:31
 * @description com.pokerfacecmy.common.mvp
 * @email cheng.meng.yuan@qq.com
 */
public interface IBaseView {
    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @param <T> T
     * @return ObservableTransformer
     */
    <T> LifecycleTransformer<T> getLifecycleTransformer();

    /**
     * 提供上下文对象
     *
     * @return context
     */
    Context getContext();

    /**
     * 展示等待的动画
     */
    void showLoading();

    /**
     * 取消等待的动画
     */
    void dismissLoading();

    /**
     * 展示展示toast消息
     *
     * @param errMsg 消息内容
     */
    default void toast(String errMsg) {
        ToastUtils.showLong(errMsg);
    }

    /**
     * 打印消息
     *
     * @param msg Object msg
     */
    default void log(Object msg) {
        LogUtils.d(msg);
    }
}
