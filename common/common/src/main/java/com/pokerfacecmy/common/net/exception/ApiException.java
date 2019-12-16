package com.pokerfacecmy.common.net.exception;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 18:08
 * @description 异常处理
 * @email cheng.meng.yuan@qq.com
 */
public class ApiException extends IOException {

    private final String code;
    private String message;
    public static final String
            DATA_NULL = "DATA_NULL",
            NOT_CARE = "NOT_CARE";


    public ApiException(Throwable throwable, String code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public static ApiException handleException(Throwable throwable, String defaultErrMsg) {
        ApiException ex;
        if (throwable instanceof SuccessWithNullDataException) {
            ex = new ApiException(throwable, DATA_NULL);
            ex.message = throwable.getMessage();
            return ex;
        } else if (throwable instanceof ServerException) {
            ServerException resultException = (ServerException) throwable;
            ex = new ApiException(resultException, resultException.getCode());
            ex.message = resultException.getMessage();
            if (TextUtils.isEmpty(ex.message)) {
                ex.message = defaultErrMsg;
            }
            return ex;
        } else {
            ex = new ApiException(throwable, NOT_CARE);
            ex.message = defaultErrMsg;
            return ex;
        }
    }

    public String getCode() {
        return code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
