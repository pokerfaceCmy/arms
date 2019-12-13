package com.pokerfacecmy.arms.ihz.contract;

import com.pokerfacecmy.arms.ihz.IhzApiResult;
import com.pokerfacecmy.arms.ihz.entity.SearchResult;
import com.pokerfacecmy.common.mvp.presenter.IBasePresenter;
import com.pokerfacecmy.common.mvp.view.IBaseView;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:43
 * @description com.pokerfacecmy.arms.ihz.contract
 * @email cheng.meng.yuan@qq.com
 */
public interface IhzContract {
    interface Model {
        /**
         * 获取搜索结果
         *
         * @param searchRequest WeakHashMap<String, Object> searchBody
         * @return Observable
         */
        @POST("verify/searchIndex")
        Observable<SearchResult> searchGet(@Body WeakHashMap<String, Object> searchRequest);

        /**
         * 修改消息读取状态
         *
         * @param alterInfoRequest WeakHashMap<String, Object> searchBody
         * @return Observable
         */
        @POST("verify/alterInformStatus")
        Observable<SearchResult> alterInformStatus(@Body WeakHashMap<String, Object> alterInfoRequest);
    }

    interface View extends IBaseView {
    }

    interface Presenter extends IBasePresenter {
        void codeNot200();

        void dataNull();
    }
}
