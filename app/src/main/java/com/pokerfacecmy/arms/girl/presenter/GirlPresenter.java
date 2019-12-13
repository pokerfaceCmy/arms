package com.pokerfacecmy.arms.girl.presenter;

import androidx.annotation.NonNull;

import com.pokerfacecmy.arms.girl.contract.GirlContract;
import com.pokerfacecmy.arms.girl.entity.GankGirl;
import com.pokerfacecmy.arms.girl.model.GirlModel;
import com.pokerfacecmy.common.mvp.presenter.BasePresenter;
import com.pokerfacecmy.common.net.subscriber.ApiObserver;

import java.util.List;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 14:05
 * @description com.pokerfacecmy.arms.girl.presenter
 * @email cheng.meng.yuan@qq.com
 */
public class GirlPresenter
        extends BasePresenter<GirlContract.View>
        implements GirlContract.Presenter {

    public GirlPresenter(@NonNull GirlContract.View view) {
        super(view);
    }


    @Override
    public void getGankGirl() {
        new GirlModel()
                .getGankGirls()
                .subscribe(new ApiObserver<List<GankGirl>>(getView(),true,true) {
                    @Override
                    public void success(List<GankGirl> gankGirls) {
                        getView().gankGirlGet(gankGirls);
                    }
                });
    }
}
