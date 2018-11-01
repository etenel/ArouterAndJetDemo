package com.eternel.jet.commonsdk.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eternel.jet.commonsdk.RouterHub;
import com.eternel.jet.commonsdk.model.GankInfo;
@Route(path = RouterHub.Gank_Service,name = "干货集中营")
public class GankInfoServiceImpl implements GankService {
    private Context mContext;
    @Override
    public GankInfo getInfo() {
        return new GankInfo("GANK");
    }

    @Override
    public void init(Context context) {
        mContext=context;
    }
}
