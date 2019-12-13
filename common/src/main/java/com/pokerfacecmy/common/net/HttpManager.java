package com.pokerfacecmy.common.net;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.pokerfacecmy.common.mvp.view.IBaseView;
import com.pokerfacecmy.common.net.annotation.ApiTag;
import com.pokerfacecmy.common.net.client.BaseOkhttpClient;
import com.pokerfacecmy.common.net.client.SimpleOkhttpClient;
import com.pokerfacecmy.common.net.exception.IExceptionHandler;
import com.trello.rxlifecycle3.LifecycleTransformer;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 10:50
 * @description com.pokerfacecmy.common.net
 * @email cheng.meng.yuan@qq.com
 */
public class HttpManager {
    /**
     * default timeout 默认超时
     */
    private static final int DEFAULT_TIMEOUT = 10000;
    /**
     * time out 超时
     */
    private int mTimeOut = DEFAULT_TIMEOUT;
    /**
     * isDebug
     */
    private boolean mIsDebug = true;
    /**
     * 默认错误信息
     */
    private String mDefaultErrorMsg = "服务器开小差了";
    /**
     * api result class 返回结果对应的类
     */
    private Class mApiResultClass;
    /**
     * tag-请求Client对，用于处理多返回结果和多url的情况
     */
    private Map<String, BaseOkhttpClient> mRetroClientMap = new HashMap<>();
    private static Application mApplication;
    /**
     * api base url 请求地址
     */
    private String mBaseUrl;
    /**
     * okhttp builder
     */
    private OkHttpClient.Builder mOkHttpClientBuilder;
    /**
     * retrofit builder
     */
    private Retrofit.Builder mRetrofitBuilder;
    /**
     * okhttp client
     */
    private BaseOkhttpClient mCommonRetroClient;
    private IExceptionHandler mExceptionHandler;

    private HttpManager() {
        generateOkHttpBuilder();
        generateRetrofitBuilder();
    }

    public static OkHttpClient getOkHttpClient() {
        return getInstance().mOkHttpClientBuilder.build();
    }

    private void generateOkHttpBuilder() {
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        mOkHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(mTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(mTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(mTimeOut, TimeUnit.MILLISECONDS)
                .cookieJar(new JavaNetCookieJar(cookieManager));
    }

    public static Retrofit getRetrofit() {
        return getInstance().mRetrofitBuilder.build();
    }

    private void generateRetrofitBuilder() {
        mRetrofitBuilder = new Retrofit.Builder();
    }

    private static class Holder {
        private final static HttpManager INSTANCE = new HttpManager();
    }

    /**
     * 静态内部类的单例模式减少开销
     *
     * @return new httpManager
     */
    private static HttpManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 初始化
     *
     * @param app Application
     * @return this
     */
    public static HttpManager init(Application app) {
        return init(app, false);
    }

    /**
     * 初始化
     *
     * @param app     Application
     * @param isDebug 是否是debug APP版本
     * @return this
     */
    public static HttpManager init(Application app, boolean isDebug) {
        return HttpManager.getInstance().setDebug(isDebug).initiate(app);
    }

    public static boolean isDebug() {
        return getInstance().mIsDebug;
    }

    /**
     * Deprecated
     * Use {@link HttpManager#init(Application, boolean)} instead
     */
    @Deprecated
    public HttpManager setDebug(boolean isDebug) {
        mIsDebug = isDebug;
        return this;
    }

    private HttpManager initiate(Application app) {
        if (mApplication != null) {
            return this;
        }
        HttpManager.mApplication = app;
        if (mIsDebug) {
            Stetho.initializeWithDefaults(app);
        }
        return this;
    }

    public static String getBaseUrl() {
        return getInstance().mBaseUrl;
    }

    /**
     * 设置host地址
     *
     * @param baseUrl String baseUrl
     * @return this
     */
    public HttpManager setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        mOkHttpClientBuilder.hostnameVerifier(new UnSafeHostnameVerifier(mBaseUrl));
        mRetrofitBuilder.baseUrl(mBaseUrl);
        return this;
    }

    private static class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        UnSafeHostnameVerifier(String host) {
            this.host = host;
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return !(this.host == null || "".equals(this.host) || !this.host.contains(hostname));
        }
    }

    public static String getDefaultErrMsg() {
        return getInstance().mDefaultErrorMsg;
    }

    /**
     * 设置默认的错误提示信息
     *
     * @param errMsg String errMsg
     * @return this
     */
    public HttpManager setDefaultErrMsg(String errMsg) {
        this.mDefaultErrorMsg = errMsg;
        return this;
    }

    public static Class getApiResultClass() {
        return getInstance().mApiResultClass;
    }

    public HttpManager setApiResultClass(Class apiResultClass) {
        this.mApiResultClass = apiResultClass;
        return this;
    }

    /**
     * 创建OkhttpClient
     *
     * @return this
     */
    public HttpManager generateClient() {
        mCommonRetroClient = new SimpleOkhttpClient().build();
        return this;
    }

    /**
     * 创建OkhttpClient
     *
     * @param tag Sting tag 用于区分不同的api host
     * @return this
     */
    public HttpManager generateClient(String tag) {
        SimpleOkhttpClient retroClient = new SimpleOkhttpClient().build();
        mRetroClientMap.put(tag, retroClient);
        return this;
    }

    public static IExceptionHandler getExceptionHandler() {
        return getInstance().mExceptionHandler;
    }

    public static <T> Observable<T> composeRequest(Observable<T> observable,
                                                   Class<?> apiService) {
        ApiTag apiTag = apiService.getAnnotation(ApiTag.class);
        if (apiTag != null) {
            return composeRequest(observable, apiTag.tag());
        }
        return composeRequest(observable);
    }

    public static <T> Observable<T> composeRequest(Observable<T> observable) {
        return observable.compose(composeApi());
    }

    public static <T> Observable<T> composeRequest(Observable<T> observable,
                                                   String tag) {
        return observable.compose(composeApi(tag));
    }

    private static <T> ObservableTransformer<T, T> composeLifecycle(IBaseView apiAction) {
        return upstream -> {
            LifecycleTransformer<T> lifecycleTransformer = apiAction == null ? null :
                    apiAction.getLifecycleTransformer();
            if (lifecycleTransformer != null) {
                upstream = upstream.compose(lifecycleTransformer);
            }
            return upstream;
        };
    }

    private static <T> ObservableTransformer<T, T> composeApi(String tag) {
        BaseOkhttpClient retroClient = getInstance().getRetroClient(tag);
        if (retroClient == null) {
            return composeApi();
        }
        return retroClient.composeApi();
    }

    private static <T> ObservableTransformer<T, T> composeApi() {
        return getInstance().mCommonRetroClient.composeApi();
    }

    private BaseOkhttpClient getRetroClient(String tag) {
        return mRetroClientMap.get(tag);
    }


    public static <T> T create(Class<T> cls) {
        ApiTag apiTag = cls.getAnnotation(ApiTag.class);
        if (apiTag != null) {
            return create(cls, apiTag.tag());
        }
        return (T) getInstance().mCommonRetroClient.create(cls);
    }

    /**
     * create apiService Interface by tag, deal with multi-url
     * 根据tag创建api服务
     */
    public static <T> T create(Class<T> cls, String tag) {
        BaseOkhttpClient retroClient = getInstance().getRetroClient(tag);
        if (retroClient == null) {
            return null;
        }
        return (T) retroClient.create(cls);
    }


}
