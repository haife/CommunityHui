package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity;

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
public class SocialCircleTopicAdapter extends BaseQuickAdapter<HRecommendMultiItemEntity, BaseViewHolder> {

    public SocialCircleTopicAdapter(int layoutResId, @Nullable List<HRecommendMultiItemEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HRecommendMultiItemEntity item) {

    }
}
