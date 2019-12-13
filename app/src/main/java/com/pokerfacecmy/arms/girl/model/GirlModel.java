package com.pokerfacecmy.arms.girl.model;

import com.pokerfacecmy.arms.girl.contract.GirlContract;
import com.pokerfacecmy.arms.girl.entity.GankGirl;
import com.pokerfacecmy.common.net.HttpManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 14:05
 * @description com.pokerfacecmy.arms.girl.model
 * @email cheng.meng.yuan@qq.com
 */
public class GirlModel implements GirlContract.Model {
    @Override
    public Observable<List<GankGirl>> getGankGirls() {
        return HttpManager
                .composeRequest(
                        HttpManager.create(GirlContract.Model.class).getGankGirls(),
                        GirlContract.Model.class
                );
    }

}
