package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.jpush.MessageEntity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/8/2
 * @desc $desc
 */
public class PushMessageListAdapter extends BaseQuickAdapter<MessageEntity, BaseViewHolder> {

    public PushMessageListAdapter(int layoutResId, @Nullable List<MessageEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {

    }
}
