package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

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
        ImageView serviceIv = helper.getView(R.id.iv_house_keeping_service_type);
        helper.setText(R.id.tv_house_keeping_service_type, item.getDicValue());
        // GlideArms.with(mContext).load(Api.IMG_URL + item.getImg()).centerCrop().into(ima);
        switch (item.getId()) {
            case 1:
                serviceIv.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.bg_house_keeping_moon_woman));
                break;
            case 2:
                serviceIv.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.bg_house_keeping_raise));
                break;
            case 3:
                serviceIv.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.bg_house_keeping_carer));
                break;
            case 4:
                serviceIv.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.bg_house_keeping_prolactin_division));
                break;
        }

    }


}
