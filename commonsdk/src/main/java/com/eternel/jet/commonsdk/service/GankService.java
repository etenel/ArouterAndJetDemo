package com.eternel.jet.commonsdk.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.eternel.jet.commonsdk.model.GankInfo;

public interface GankService extends IProvider {
    GankInfo getInfo();
}
