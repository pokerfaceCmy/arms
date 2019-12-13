package com.pokerfacecmy.arms.girl.contract;

import com.pokerfacecmy.arms.girl.entity.GankGirl;
import com.pokerfacecmy.common.mvp.presenter.IBasePresenter;
import com.pokerfacecmy.common.mvp.view.IBaseView;
import com.pokerfacecmy.common.net.annotation.ApiTag;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 14:05
 * @description com.pokerfacecmy.arms.girl.contract
 * @email cheng.meng.yuan@qq.com
 */
public interface GirlContract {
    @ApiTag(tag = "Gank")
    interface Model {
        /**
         * 获取妹子列表
         *
         * @return Observable<List < GankGirl>>
         */
        @GET("福利/10/1")
        Observable<List<GankGirl>> getGankGirls();
    }

    interface View extends IBaseView {
        /**
         * 成功获取妹子图
         *
         * @param data list gankGirl
         */
        void gankGirlGet(List<GankGirl> data);
    }

    interface Presenter extends IBasePresenter {
        /**
         * 网络请求获取妹子图
         */
        void getGankGirl();
    }
}
