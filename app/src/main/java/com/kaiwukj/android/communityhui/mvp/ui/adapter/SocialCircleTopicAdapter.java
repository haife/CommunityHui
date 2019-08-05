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
import androidx.core.content.ContextCompat;

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

    private int[] imageArray = {R.mipmap.icon_social_circle_the_user, R.mipmap.icon_social_circle_travel, R.mipmap.ic_socail_circle_pet, R.mipmap.icon_social_circle_house
            , R.mipmap.ic_social_circle_carport};

    public SocialCircleTopicAdapter(int layoutResId, @Nullable List<CircleCardResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleCardResult item) {
        ImageView iv = helper.getView(R.id.iv_social_circle_topic);
        helper.setText(R.id.tv_social_circle_topic, item.getDicValue());
        iv.setBackground(ContextCompat.getDrawable(mContext, imageArray[helper.getAdapterPosition()]));
        //Glide.with(mContext).load(Api.IMG_URL + item.getImg()).into(iv);
    }
}
