package com.yyxx.wechatfp.network.updateCheck.github.bean;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.yyxx.wechatfp.BuildConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jason on 2017/9/10.
 */

public class GithubLatestInfo {


    @SerializedName("html_url")
    public String contentUrl;

    @SerializedName("name")
    public String version;

    @SerializedName("body")
    public String content;

    @SerializedName("created_at")
    public Date date;


    public List<GithubAssetsInfo> assets = new ArrayList<>();

    @Nullable
    public String getDownloadUrl() {
        if (assets.size() == 0) {
            return null;
        }
        String lowerProductName = BuildConfig.APP_PRODUCT_NAME.toLowerCase();
        for (GithubAssetsInfo asset : assets) {
            if (asset == null || TextUtils.isEmpty(asset.name) || TextUtils.isEmpty(asset.url)) {
                continue;
            }
            if (asset.name.toLowerCase().contains(lowerProductName)) {
                return asset.url;
            }
        }
        return null;
    }

    public boolean isDataComplete() {
        if (TextUtils.isEmpty(getDownloadUrl())) {
            return false;
        }
        if (TextUtils.isEmpty(version)) {
            return false;
        }
        if (TextUtils.isEmpty(contentUrl)) {
            return false;
        }
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
