package com.pokerfacecmy.arms.ihz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pokerfacecmy.arms.R;
import com.pokerfacecmy.arms.ihz.contract.IhzContract;
import com.pokerfacecmy.arms.ihz.presenter.IhzPresenter;
import com.pokerfacecmy.common.mvp.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pokerfaceCmy
 */
public class IhzActivity
        extends BaseActivity<IhzContract.Presenter>
        implements IhzContract.View {

    @BindView(R.id.btn_code_not_200)
    Button btnCodeNot200;
    @BindView(R.id.btn_data_null)
    Button btnDataNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_ihz;
    }

    @Override
    public IhzContract.Presenter onBindPresenter() {
        return new IhzPresenter(this);
    }

    @OnClick({R.id.btn_code_not_200, R.id.btn_data_null})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_code_not_200:
                getPresenter().codeNot200();
                break;
            case R.id.btn_data_null:
                getPresenter().dataNull();
                break;
            default:
                break;
        }
    }
}
