package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc 家政服务
 */
public class HouseKeepServiceAdapter extends BaseQuickAdapter<HomeServiceEntity, BaseViewHolder> {
    public HouseKeepServiceAdapter(int layoutResId, @Nullable ArrayList<HomeServiceEntity> data, Context context) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeServiceEntity item) {
        ImageView ima = helper.getView(R.id.iv_house_keeping_service_type);
        helper.setText(R.id.tv_house_keeping_service_type, item.getDicValue());
        GlideArms.with(mContext).load(Api.IMG_URL + item.getImg()).centerCrop().into(ima);
    }


}
