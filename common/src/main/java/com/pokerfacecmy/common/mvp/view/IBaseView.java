package com.pokerfacecmy.common.mvp.view;

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
    void toast(String errMsg);

    /**
     * 打印消息
     *
     * @param msg Object msg
     */
    void log(Object msg);
}
