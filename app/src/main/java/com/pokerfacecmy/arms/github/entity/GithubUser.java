package com.pokerfacecmy.arms.github.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author pokerfaceCmy
 * @date 2019/12/12 16:15
 * @description com.pokerfacecmy.arms.github.entity
 * @email cheng.meng.yuan@qq.com
 */
public class GithubUser {
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
