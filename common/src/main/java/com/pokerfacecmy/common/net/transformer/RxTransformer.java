package com.pokerfacecmy.common.net.transformer;

import com.pokerfacecmy.common.mvp.view.IBaseView;
import com.trello.rxlifecycle3.LifecycleTransformer;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 10:48
 * @description com.pokerfacecmy.common.net.transformer
 * @email cheng.meng.yuan@qq.com
 */
public class RxTransformer {
    public static <T> ObservableTransformer<T, T> apiIo2Main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> io2Main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> io2Main(IBaseView apiAction) {
        return upstream -> {
            upstream = upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            LifecycleTransformer<T> lifecycleTransformer = apiAction == null ? null : apiAction
                    .getLifecycleTransformer();
            if (lifecycleTransformer != null) {
                upstream = upstream.compose(lifecycleTransformer);
            }
            return upstream;
        };
    }
}
