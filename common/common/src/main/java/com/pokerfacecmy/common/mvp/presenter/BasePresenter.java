package com.pokerfacecmy.common.mvp.presenter;

import androidx.annotation.NonNull;

import com.pokerfacecmy.common.mvp.view.IBaseView;

import java.lang.ref.WeakReference;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 9:32
 * @description com.pokerfacecmy.common.mvp.presenter
 * @email cheng.meng.yuan@qq.com
 */
public class BasePresenter<V extends IBaseView>
        implements IBasePresenter {
    private WeakReference<V> mViewRef;

    public BasePresenter(@NonNull V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public V getView() {
        return mViewRef.get();
    }

}
