package com.pokerfacecmy.arms.github.presenter;

import androidx.annotation.NonNull;

import com.pokerfacecmy.arms.github.contract.GithubContract;
import com.pokerfacecmy.arms.github.entity.GithubUser;
import com.pokerfacecmy.arms.github.model.GithubModel;
import com.pokerfacecmy.common.mvp.presenter.BasePresenter;
import com.pokerfacecmy.common.net.subscriber.ApiObserver;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:11
 * @description com.pokerfacecmy.arms.github.presenter
 * @email cheng.meng.yuan@qq.com
 */
public class GithubPresenter
        extends BasePresenter<GithubContract.View>
        implements GithubContract.Presenter {
    public GithubPresenter(@NonNull GithubContract.View view) {
        super(view);
    }

    @Override
    public void getGithubUserInfo(String userName) {
        new GithubModel()
                .getUser(userName)
                .subscribe(new ApiObserver<GithubUser>() {
                    @Override
                    public void success(GithubUser githubUser) {
                        getView().githubUserInfoGet(githubUser);
                    }
                });
    }
}
