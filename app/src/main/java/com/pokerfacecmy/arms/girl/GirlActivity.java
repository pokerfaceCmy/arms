package com.pokerfacecmy.arms.girl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pokerfacecmy.arms.R;
import com.pokerfacecmy.arms.girl.contract.GirlContract;
import com.pokerfacecmy.arms.girl.entity.GankGirl;
import com.pokerfacecmy.arms.girl.presenter.GirlPresenter;
import com.pokerfacecmy.common.mvp.view.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pokerfaceCmy
 */
public class GirlActivity
        extends BaseActivity<GirlContract.Presenter>
        implements GirlContract.View {
    private GankGirlAdapter adapter = new GankGirlAdapter();

    @BindView(R.id.btn_girl)
    Button btnGirl;
    @BindView(R.id.rv_girl)
    RecyclerView rvGirl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvGirl.setLayoutManager(new LinearLayoutManager(mContext));
        rvGirl.setAdapter(adapter);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_girl;
    }

    @Override
    public GirlContract.Presenter onBindPresenter() {
        return new GirlPresenter(this);
    }

    @Override
    public void gankGirlGet(List<GankGirl> data) {
        btnGirl.setVisibility(View.GONE);
        rvGirl.setVisibility(View.VISIBLE);
        adapter.setNewData(data);
    }

    @OnClick({R.id.btn_girl, R.id.rv_girl})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_girl) {
            getPresenter().getGankGirl();
        }
    }

    private class GankGirlAdapter extends BaseQuickAdapter<GankGirl, BaseViewHolder> {
        GankGirlAdapter() {
            super(R.layout.item_gank_girl);
        }

        @Override
        protected void convert(BaseViewHolder helper, GankGirl item) {
            Glide.with(mContext)
                    .load(item.getUrl())
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) helper.getView(R.id.iv_item_girl));
        }
    }
}
