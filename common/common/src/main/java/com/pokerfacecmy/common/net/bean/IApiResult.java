package com.pokerfacecmy.common.net.bean;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 9:36
 * @description Api返回数据结构接口
 * @email cheng.meng.yuan@qq.com
 */
public interface IApiResult<T> {
    /**
     * 是否成功
     *
     * @return true for success
     */
    boolean isSuccess();

    /**
     * 返回的数据
     *
     * @return api data
     */
    T getData();

    /**
     * 返回的message
     *
     * @return message returned
     */
    String getResultMsg();

    /**
     * 返回的code
     *
     * @return code returned
     */
    String getResultCode();

    /**
     * 返回data数据的json字段名
     *
     * @return data field
     */
    String getDataField();
}
