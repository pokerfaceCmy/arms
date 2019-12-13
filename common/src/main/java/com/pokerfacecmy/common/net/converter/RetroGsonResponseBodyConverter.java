package com.pokerfacecmy.common.net.converter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokerfacecmy.common.net.bean.IApiResult;
import com.pokerfacecmy.common.net.exception.ServerException;
import com.pokerfacecmy.common.net.exception.SuccessWithNullDataException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 15:09
 * @description com.pokerfacecmy.common.net.converter
 * @email cheng.meng.yuan@qq.com
 */
class RetroGsonResponseBodyConverter<T, ApiResultType extends IApiResult> implements
        Converter<ResponseBody, T> {
    private final Gson gson;
    private Type type;
    private Class<ApiResultType> apiClass;

    RetroGsonResponseBodyConverter(Gson gson, Type type, Class<ApiResultType> apiClass) {
        this.gson = gson;
        this.type = type;
        this.apiClass = apiClass;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            ApiResultType apiResult = gson.fromJson(response, apiClass);
            if (!apiResult.isSuccess()) {
                throw new ServerException(apiResult.getResultMsg(), apiResult.getResultCode());
            } else if (type.equals(apiClass)) {
                //如果需求类型为ApiResult本身（一般情况下为无具体返回内容，只关心成功与否），则强转t
                //noinspection unchecked
                return (T) apiResult;
            } else if (apiResult.getData() == null || "".equals(apiResult.getData())) {
                throw new SuccessWithNullDataException(apiResult.getResultMsg(),
                        apiResult.getResultCode());
            }
            //if there's no data field, means that the result itself is the returned data
            //如果未设置data字段，则认为返回的整个结果就是数据段
            if (TextUtils.isEmpty(apiResult.getDataField())) {
                return gson.fromJson(response, type);
            }
            //parse data field 解析data数据
            return gson.fromJson(((JsonObject) new JsonParser().parse(response)).get(apiResult.getDataField()), type);
        } finally {
            value.close();
        }
    }
}

