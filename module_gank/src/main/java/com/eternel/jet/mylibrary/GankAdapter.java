package com.eternel.jet.mylibrary;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class GankAdapter extends BaseQuickAdapter<GankItemBean.ResultsBean,BaseViewHolder> {

    public GankAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankItemBean.ResultsBean item) {
        Glide.with(mContext).load(item.getUrl())
                .apply(new RequestOptions().centerCrop())
                .into((ImageView)helper.getView(R.id.iv_image));
    }
}
