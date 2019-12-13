package com.pokerfacecmy.arms.github.contract;

import com.pokerfacecmy.arms.github.entity.GithubUser;
import com.pokerfacecmy.common.mvp.presenter.IBasePresenter;
import com.pokerfacecmy.common.mvp.view.IBaseView;
import com.pokerfacecmy.common.net.annotation.ApiTag;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:11
 * @description com.pokerfacecmy.arms.github.contract
 * @email cheng.meng.yuan@qq.com
 */
public interface GithubContract {
    @ApiTag(tag = "Github")
    interface Model {
        /**
         * @param userName 用户名
         * @return Observable<GithubUser>
         */
        @GET("users/{userName}")
        Observable<GithubUser> getUser(@Path("userName") String userName);
    }

    interface View extends IBaseView {
        /**
         * 返回用户信息
         *
         * @param githubUser 用户信息
         */
        void githubUserInfoGet(GithubUser githubUser);
    }

    interface Presenter extends IBasePresenter {
        /**
         * 通过用户名获取用户信息
         *
         * @param userName 用户名
         */
        void getGithubUserInfo(String userName);
    }
}
