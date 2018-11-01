package com.eternel.jet.mylibrary.ui.gank;

import android.annotation.SuppressLint;
import android.util.Log;

import com.eternel.jet.mylibrary.GankItemBean;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GankViewModel extends ViewModel {
    private Retrofit retrofit;
    private MutableLiveData<List<GankItemBean.ResultsBean>> datas;
    private int pager = 0;
    private int items = 10;
    private MutableLiveData<Boolean> refreshPager;

    public MutableLiveData<Boolean> getRefreshPager() {
        if (refreshPager == null) {
            refreshPager = new MutableLiveData<>();
        }
        return refreshPager;
    }

    public LiveData<List<GankItemBean.ResultsBean>> getDatas() {
        if (datas == null) {
            datas = new MutableLiveData<List<GankItemBean.ResultsBean>>();
        }
        return datas;
    }

    //加载网络数据
    @SuppressLint("CheckResult")
    public void loadDatas(final boolean refresh) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://gank.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        retrofit.create(GankService.class)
                .getGirlList(items, refresh ? 0 : pager + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankItemBean>() {
                    @Override
                    public void accept(GankItemBean gankItemBean) throws Exception {
                        refreshPager.setValue(refresh);
                        if (refresh) {
                            pager = 0;
                            datas.setValue(gankItemBean.getResults());
                        } else {
                            if (!gankItemBean.isError() && gankItemBean.getResults() != null && gankItemBean.getResults().size() > 0) {
                                pager++;
                                datas.setValue(gankItemBean.getResults());
                            }
                        }
                    }
                });
    }

    interface GankService {
        @GET("/api/data/福利/{num}/{page}")
        Observable<GankItemBean> getGirlList(@Path("num") int num, @Path("page") int page);
    }
}
