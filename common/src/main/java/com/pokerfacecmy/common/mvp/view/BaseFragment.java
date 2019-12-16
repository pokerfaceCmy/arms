package com.pokerfacecmy.common.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pokerfacecmy.common.mvp.presenter.IBasePresenter;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pokerfaceCmy
 * @date 2019/12/16 9:52
 * @description com.pokerfacecmy.common.mvp.view
 * @email cheng.meng.yuan@qq.com
 */
public abstract class BaseFragment<P extends IBasePresenter>
        extends RxFragment
        implements IBaseView {
    private Unbinder unbinder;
    private P mPresenter;
    private View mView;
    protected Context mContext;

    private ProgressDialog mProgressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        mContext = getContext();
        initView(mView);

        initDialog();
        return mView;
    }

    private void initDialog() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
    }


    @Nullable
    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 初始化
     *
     * @param mView View
     */
    protected abstract void initView(View mView);


    public View getmView() {
        return mView;
    }

    /**
     * 绑定布局文件
     *
     * @return LayoutId
     */
    protected abstract int setLayoutId();

    /**
     * 创建 Presenter
     *
     * @return Presenter
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


    @Override
    public <T> LifecycleTransformer<T> getLifecycleTransformer() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void showLoading() {
        assert getActivity() != null;
        if (mProgressDialog != null &&
                !mProgressDialog.isShowing() &&
                !getActivity().isFinishing() &&
                !getActivity().isDestroyed()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        assert getActivity() != null;
        if (mProgressDialog != null &&
                mProgressDialog.isShowing() &&
                !getActivity().isFinishing() &&
                !getActivity().isDestroyed()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
