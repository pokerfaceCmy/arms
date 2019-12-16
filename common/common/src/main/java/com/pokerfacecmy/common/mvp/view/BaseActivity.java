package com.pokerfacecmy.common.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.pokerfacecmy.common.mvp.presenter.IBasePresenter;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 9:30
 * @description com.pokerfacecmy.common.mvp
 * @email cheng.meng.yuan@qq.com
 */
public abstract class BaseActivity<P extends IBasePresenter>
        extends RxAppCompatActivity
        implements IBaseView {
    private ProgressDialog mProgressDialog;
    private P mPresenter;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提供上下文对象
        mContext = this;
        setContentView(setContentView());
        ButterKnife.bind(this);
        initImmersionBar();
        initDialog();
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 绑定布局
     *
     * @return R.id.xxx
     */
    protected abstract int setContentView();

    /**
     * ProgressDialog
     */
    private void initDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }

    /**
     * 沉浸式状态栏
     */
    private void initImmersionBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .fitsSystemWindowsInt(true, Color.parseColor("#3f51b5"))
                .statusBarView(ViewGroup.inflate(this, setContentView(), null))
                .init();
    }

    /**
     * 创建 Presenter
     *
     * @return presenter
     */
    public abstract P onBindPresenter();

    /**
     * 获取 Presenter 对象，在需要获取时才创建`Presenter`，起到懒加载作用
     */
    public P getPresenter() {
        if (mPresenter == null) {
            mPresenter = onBindPresenter();
        }
        return mPresenter;
    }


    /**
     * 以设计图为750*1334尺寸做适配,适配为mdpi,参考:<p>
     * android屏幕适配终结者 https://blankj.com/2018/12/18/android-adapt-screen-killer/}
     *
     * @return Resources
     */
    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),
                750);
    }

    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @return LifecycleTransformer
     */
    @Override
    public <T> LifecycleTransformer<T> getLifecycleTransformer() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    /**
     * 展示等待的动画
     */
    @Override
    public void showLoading() {
        if (mProgressDialog != null &&
                !mProgressDialog.isShowing() &&
                !isFinishing() &&
                !isDestroyed()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消等待的动画
     */
    @Override
    public void dismissLoading() {
        if (mProgressDialog != null &&
                mProgressDialog.isShowing() &&
                !isFinishing() &&
                !isDestroyed()) {
            mProgressDialog.dismiss();
        }
    }

}
