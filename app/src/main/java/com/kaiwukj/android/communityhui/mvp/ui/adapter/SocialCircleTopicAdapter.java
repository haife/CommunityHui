package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc 社交动态适配器
 */
public class SocialCircleTopicAdapter extends BaseQuickAdapter<CircleCardResult, BaseViewHolder> {

    public SocialCircleTopicAdapter(int layoutResId, @Nullable List<CircleCardResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleCardResult item) {
        ImageView iv = helper.getView(R.id.iv_social_circle_topic);
        helper.setText(R.id.tv_social_circle_topic, item.getDicValue());
        Glide.with(mContext).load(Api.IMG_URL + item.getImg()).into(iv);
    }
}
