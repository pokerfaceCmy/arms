package com.pokerfacecmy.arms.ihz;

import com.pokerfacecmy.common.net.bean.IApiResult;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 11:16
 * @description com.pokerfacecmy.arms.ihz
 * @email cheng.meng.yuan@qq.com
 */
public class IhzApiResult<T> implements IApiResult<T> {
    private static final String SUCCESS_CODE = "200";
    private String code;
    private String msg;
    private T data;

    @Override
    public boolean isSuccess() {
        return code.equals(SUCCESS_CODE);
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getResultMsg() {
        return msg;
    }

    @Override
    public String getResultCode() {
        return code;
    }

    @Override
    public String getDataField() {
        return "data";
    }
}
