package com.pokerfacecmy.arms.github;

import android.text.TextUtils;

import com.pokerfacecmy.common.net.bean.IApiResult;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 11:11
 * @description com.pokerfacecmy.arms.github
 * @email cheng.meng.yuan@qq.com
 */
public class GithubApiResult implements IApiResult<GithubApiResult> {
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return TextUtils.isEmpty(message);
    }

    @Override
    public GithubApiResult getData() {
        return this;
    }

    @Override
    public String getResultMsg() {
        return message;
    }

    @Override
    public String getResultCode() {
        return "";
    }

    @Override
    public String getDataField() {
        return "";
    }
}
