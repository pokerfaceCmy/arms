package com.pokerfacecmy.common.net.exception;

import java.io.IOException;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 15:11
 * @description com.pokerfacecmy.common.net.exception
 * @email cheng.meng.yuan@qq.com
 */
public class SuccessWithNullDataException extends IOException {
    private String code;

    public SuccessWithNullDataException(String resultMsg, String resultCode) {
        super(resultMsg);
        this.code = resultCode;
    }

    public String getCode() {
        return code;
    }
}
