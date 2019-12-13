package com.pokerfacecmy.common.net.client;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.pokerfacecmy.common.net.HttpManager;
import com.pokerfacecmy.common.net.converter.RetroGsonConverterFactory;
import com.pokerfacecmy.common.net.exception.ApiException;
import com.pokerfacecmy.common.net.exception.IExceptionHandler;
import com.pokerfacecmy.common.net.func.ExceptionHandleFunc;
import com.pokerfacecmy.common.net.transformer.RxTransformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableTransformer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 10:43
 * @description com.pokerfacecmy.common.net.client
 * @email cheng.meng.yuan@qq.com
 */
public abstract class BaseOkhttpClient<Client extends BaseOkhttpClient> {
    private final List<Interceptor> networkInterceptors = new ArrayList<>();
    private final List<Interceptor> interceptors = new ArrayList<>();
    private String mBaseUrl;
    private boolean mIsDebug = true;
    private String mDefaultErrMsg = "";
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private OkHttpClient.Builder mOkHttpClientBuilder;
    private Retrofit.Builder mRetrofitBuilder;
    private IExceptionHandler mExceptionHandler;

    BaseOkhttpClient() {
        mBaseUrl = HttpManager.getBaseUrl();
        //        mRetryCount = HttpManager.getRetryCount();
        //        mRetryDelay = HttpManager.getRetryDelay();
        mIsDebug = HttpManager.isDebug();
        mDefaultErrMsg = HttpManager.getDefaultErrMsg();
        mExceptionHandler = HttpManager.getExceptionHandler();
        if (mExceptionHandler == null) {
            mExceptionHandler = throwable -> ApiException.handleException(throwable,
                    mDefaultErrMsg);
        }

        generateOkClient();
        generateRetrofit();
    }

    /**
     * 初始化OkHttp
     * init okhttp
     */
    private void generateOkClient() {
        mOkHttpClientBuilder = HttpManager.getOkHttpClient().newBuilder();
        setOkHttpClientBuilder(mOkHttpClientBuilder);
    }

    private void setOkHttpClientBuilder(OkHttpClient.Builder okHttpClientBuilder) {
        if (interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                okHttpClientBuilder.addInterceptor(interceptor);
            }
        }
        if (networkInterceptors.size() > 0) {
            for (Interceptor interceptor : networkInterceptors) {
                okHttpClientBuilder.addNetworkInterceptor(interceptor);
            }
        }
        if (mIsDebug) {
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
            okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel
                    (HttpLoggingInterceptor.Level.BODY));
        }
    }

    /**
     * 初始化Retrofit
     * init Retrofit
     */
    private void generateRetrofit() {
        mRetrofitBuilder = HttpManager.getRetrofit().newBuilder()
                .baseUrl(mBaseUrl);
        setRetrofitBuilder(mRetrofitBuilder);
    }

    private void setRetrofitBuilder(Retrofit.Builder retrofitBuilder) {
        if (HttpManager.getApiResultClass() == null) {
            retrofitBuilder
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            return;
        }
        retrofitBuilder
                .addConverterFactory(RetroGsonConverterFactory.create(HttpManager.getApiResultClass()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    /**
     * 构建client
     * build client
     */
    public Client build() {
        mOkHttpClient = mOkHttpClientBuilder.build();
        mRetrofitBuilder.client(mOkHttpClient);
        mRetrofit = mRetrofitBuilder.build();
        //noinspection unchecked
        return (Client) this;
    }

    /**
     * 设置重试、线程转换
     * set retry and thread switch
     */
    public <T> ObservableTransformer<T, T> composeApi() {
        return observable -> observable
                .compose(RxTransformer.apiIo2Main())
                .onErrorResumeNext(new ExceptionHandleFunc<>(mExceptionHandler));
        //                .retryWhen(new RetryExceptionFunc(mRetryCount, mRetryDelay));
    }

    /**
     * 构建retrofit请求接口
     * create retrofit interface
     */
    public <T> T create(Class<T> cls) {
        return mRetrofit.create(cls);
    }
}
