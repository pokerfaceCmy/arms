package com.pokerfacecmy.arms.ihz.model;

import com.pokerfacecmy.arms.ihz.IhzApiResult;
import com.pokerfacecmy.arms.ihz.contract.IhzContract;
import com.pokerfacecmy.arms.ihz.entity.SearchResult;
import com.pokerfacecmy.common.net.HttpManager;

import java.util.WeakHashMap;

import io.reactivex.Observable;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:43
 * @description com.pokerfacecmy.arms.ihz.model
 * @email cheng.meng.yuan@qq.com
 */
public class IhzModel implements IhzContract.Model {

    @Override
    public Observable<SearchResult> searchGet(WeakHashMap<String, Object> searchRequest) {
        return HttpManager
                .composeRequest(
                        HttpManager.create(IhzContract.Model.class).searchGet(searchRequest),
                        IhzContract.Model.class
                );
    }

    @Override
    public Observable<SearchResult> alterInformStatus(WeakHashMap<String, Object> alterInfoRequest) {
        return HttpManager
                .composeRequest(
                        HttpManager.create(IhzContract.Model.class).alterInformStatus(alterInfoRequest),
                        IhzContract.Model.class
                );
    }
}
