package com.pokerfacecmy.arms;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.pokerfacecmy.arms.girl.GankApiResult;
import com.pokerfacecmy.arms.github.GithubApiResult;
import com.pokerfacecmy.arms.ihz.IhzApiResult;
import com.pokerfacecmy.common.net.HttpManager;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 11:07
 * @description com.pokerfacecmy.arms
 * @email cheng.meng.yuan@qq.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        HttpManager
                .init(this, true)
                .setBaseUrl("https://whiot.ihaozhuo.com/")
                .setDefaultErrMsg("服务器开小差了")
                .setApiResultClass(IhzApiResult.class)
                .generateClient()

                .setBaseUrl("https://api.github.com/")
                .setDefaultErrMsg("Github开小差了")
                .setApiResultClass(GithubApiResult.class)
                .generateClient("Github")

                .setBaseUrl("http://gank.io/api/data/")
                .setDefaultErrMsg("妹子不见了")
                .setApiResultClass(GankApiResult.class)
                .generateClient("Gank")
        ;
    }
}
