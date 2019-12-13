package com.pokerfacecmy.arms;

import android.os.Bundle;

import com.pokerfacecmy.common.mvp.presenter.IBasePresenter;
import com.pokerfacecmy.common.mvp.view.BaseActivity;

/**
 * @author pokerfaceCmy
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public IBasePresenter onBindPresenter() {
        return null;
    }
}
