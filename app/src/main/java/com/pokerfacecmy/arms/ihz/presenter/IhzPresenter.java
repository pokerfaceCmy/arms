package com.pokerfacecmy.arms.ihz.presenter;

import androidx.annotation.NonNull;

import com.pokerfacecmy.arms.ihz.IhzApiResult;
import com.pokerfacecmy.arms.ihz.contract.IhzContract;
import com.pokerfacecmy.arms.ihz.entity.SearchResult;
import com.pokerfacecmy.arms.ihz.model.IhzModel;
import com.pokerfacecmy.common.mvp.presenter.BasePresenter;
import com.pokerfacecmy.common.net.subscriber.ApiObserver;

import java.util.WeakHashMap;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:43
 * @description com.pokerfacecmy.arms.ihz.presenter
 * @email cheng.meng.yuan@qq.com
 */
public class IhzPresenter
        extends BasePresenter<IhzContract.View>
        implements IhzContract.Presenter {

    public IhzPresenter(@NonNull IhzContract.View view) {
        super(view);
    }

    @Override
    public void codeNot200() {
        WeakHashMap<String, Object> par = new WeakHashMap<>();
        par.put("userId", "bmf9mu2hkcorcskufiu0");
        par.put("indexName", "test");
        new IhzModel()
                .searchGet(par)
                .subscribe(new ApiObserver<SearchResult>(getView(), true, true) {
                    @Override
                    public void success(SearchResult searchResult) {

                    }
                });
    }

    @Override
    public void dataNull() {
        WeakHashMap<String, Object> par = new WeakHashMap<>();
        par.put("userId", "bmf9mu2hkcorcskufiu0");
        par.put("id", "bmjsjmqhkcose9b9fd1g");
        new IhzModel()
                .alterInformStatus(par)
                .subscribe(new ApiObserver<SearchResult>() {
                    @Override
                    public void success(SearchResult ihzApiResult) {

                    }

                    @Override
                    public void dataNull() {
                        super.dataNull();
                        getView().toast("访问成功了data为空");
                    }
                });

    }
}
