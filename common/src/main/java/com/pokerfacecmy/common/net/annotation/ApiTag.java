package com.pokerfacecmy.common.net.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 10:33
 * @description 用于baseUrl不同的接口, 创建新的RetroClient
 * @email cheng.meng.yuan@qq.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiTag {
    /**
     * 标记不同baseUrl的tag
     *
     * @return string
     */
    String tag();
}
