package com.pokerfacecmy.common.net.exception;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 18:23
 * @description 异常处理接口
 * @email cheng.meng.yuan@qq.com
 */
public interface IExceptionHandler {
    /**
     * 异常处理接口
     *
     * @param throwable throwable
     * @return ApiException
     */
    ApiException handleException(Throwable throwable);
}
