package com.pokerfacecmy.arms.girl;

import com.pokerfacecmy.common.net.bean.IApiResult;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 11:15
 * @description com.pokerfacecmy.arms.girl
 * @email cheng.meng.yuan@qq.com
 */
public class GankApiResult<T> implements IApiResult<T> {
    private boolean error;
    private T results;

    @Override
    public boolean isSuccess() {
        return !error;
    }

    @Override
    public T getData() {
        return results;
    }

    @Override
    public String getResultMsg() {
        return "";
    }

    @Override
    public String getResultCode() {
        return "";
    }

    @Override
    public String getDataField() {
        return "results";
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
