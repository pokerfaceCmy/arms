package com.pokerfacecmy.arms.github.model;

import com.pokerfacecmy.arms.github.contract.GithubContract;
import com.pokerfacecmy.arms.github.entity.GithubUser;
import com.pokerfacecmy.common.net.HttpManager;

import io.reactivex.Observable;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:11
 * @description com.pokerfacecmy.arms.github.model
 * @email cheng.meng.yuan@qq.com
 */
public class GithubModel implements GithubContract.Model {
    @Override
    public Observable<GithubUser> getUser(String userName) {
        return HttpManager
                .composeRequest(
                        HttpManager.create(GithubContract.Model.class).getUser(userName),
                        GithubContract.Model.class
                );
    }
}
