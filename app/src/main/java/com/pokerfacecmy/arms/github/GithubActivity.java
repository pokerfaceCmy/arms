package com.pokerfacecmy.arms.github;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.pokerfacecmy.arms.R;
import com.pokerfacecmy.arms.github.contract.GithubContract;
import com.pokerfacecmy.arms.github.entity.GithubUser;
import com.pokerfacecmy.arms.github.presenter.GithubPresenter;
import com.pokerfacecmy.common.mvp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author pokerfaceCmy
 */
public class GithubActivity
        extends BaseActivity<GithubContract.Presenter>
        implements GithubContract.View {

    @BindView(R.id.btn_github_search)
    Button btnGithubSearch;
    @BindView(R.id.et_github)
    AppCompatEditText etGithub;
    @BindView(R.id.iv_github_avatar)
    AppCompatImageView ivGithubAvatar;
    @BindView(R.id.tv_github_name)
    TextView tvGithubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ButterKnife.bind(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_github;
    }

    @Override
    public GithubContract.Presenter onBindPresenter() {
        return new GithubPresenter(this);
    }

    @Override
    public void githubUserInfoGet(GithubUser githubUser) {
        tvGithubName.setText(githubUser.getName());
        Glide.with(ivGithubAvatar)
                .load(githubUser.getAvatarUrl())
                .transition(new DrawableTransitionOptions().crossFade())
                .into(ivGithubAvatar);
    }

    @OnClick(R.id.btn_github_search)
    public void onViewClicked(View view) {
        assert etGithub.getText() != null;
        if (TextUtils.isEmpty(etGithub.getText().toString())) {
            new AlertDialog.Builder(mContext).setTitle("提示")
                    .setMessage("请输入用户名")
                    .setPositiveButton("确定", (dialog, which) -> {
                    }).setNegativeButton("取消", (dialog, which) -> {
            }).show();
            return;
        }
        if (view.getId() == R.id.btn_github_search) {
            getPresenter().getGithubUserInfo(etGithub.getText().toString());
        }

    }
}
