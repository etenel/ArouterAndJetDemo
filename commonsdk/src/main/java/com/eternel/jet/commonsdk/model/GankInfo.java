package com.eternel.jet.commonsdk.model;

public class GankInfo {
    private String gankName;

    public GankInfo(String gankName) {
        this.gankName = gankName;
    }

    public String getGankName() {
        return gankName == null ? "" : gankName;
    }

    public void setGankName(String gankName) {
        this.gankName = gankName;
    }
}
