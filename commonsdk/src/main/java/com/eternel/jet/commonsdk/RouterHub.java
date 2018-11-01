package com.eternel.jet.commonsdk;

public interface RouterHub {
    String APP = "/app";//宿主app组件
    String GANK = "/gank";//gank组件
    String SERVICE = "service";
    /*
     *gank分组
     */
    String Gank_Activity = GANK + "/GankActivity";
    String Gank_Fragment = GANK + "/GankFragment";
    String Gank_Service = GANK + SERVICE + "/GankService";
    String APP_MainActivity = APP + "/MainActivity";
}
