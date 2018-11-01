package com.eternel.jet.mylibrary;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.eternel.jet.mylibrary.ui.gank.GankViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

@Route(path = "/gank/GankActivity")
public class GankActivity extends AppCompatActivity {

    private RecyclerView list;
    private GankViewModel mGankViewModel;
    private GankAdapter gankAdapter;
    private SwipeRefreshLayout sRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gank_activity);
        list = findViewById(R.id.rl_list);
        sRefresh = findViewById(R.id.srefresh);
        mGankViewModel = ViewModelProviders.of(this).get(GankViewModel.class);
        gankAdapter = new GankAdapter(R.layout.item_list);
        list.setAdapter(gankAdapter);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        //上拉加载更多
        gankAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mGankViewModel.loadDatas(false);
            }
        }, list);
//        下拉刷新
        sRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGankViewModel.loadDatas(true);
            }
        });
        //下拉刷新，上拉加载状态更多监听
        mGankViewModel.getRefreshPager().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    gankAdapter.loadMoreComplete();
                }else{
                    sRefresh.setRefreshing(false);
                }
            }
        });
        //数据改变监听
        mGankViewModel.getDatas().observe(this, new Observer<List<GankItemBean.ResultsBean>>() {
            @Override
            public void onChanged(List<GankItemBean.ResultsBean> resultsBeans) {
                Log.e("gankActivity",resultsBeans.size()+"");
                if (mGankViewModel.getRefreshPager().getValue()) {
                    gankAdapter.setNewData(resultsBeans);
                } else {
                    gankAdapter.addData(resultsBeans);
                }
            }
        });
        sRefresh.setRefreshing(true);
        mGankViewModel.loadDatas(true);
    }
}
