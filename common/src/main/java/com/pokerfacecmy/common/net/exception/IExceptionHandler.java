package com.pokerfacecmy.common.net.exception;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 18:23
 * @description com.pokerfacecmy.common.net.exception
 * @email cheng.meng.yuan@qq.com
 */
public interface IExceptionHandler {
    ApiException handleException(Throwable throwable);
}
