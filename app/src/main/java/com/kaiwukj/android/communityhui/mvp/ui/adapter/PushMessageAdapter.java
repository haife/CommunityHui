package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.jpush.MessageEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/8/5
 * @desc $desc
 */
public class PushMessageAdapter extends BaseQuickAdapter<MessageEntity, BaseViewHolder> {

    public PushMessageAdapter(int layoutResId, @Nullable List<MessageEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
        String time = format.format(new Date(item.getCreateTime()));
        helper.setText(R.id.tv_push_message_time, time).setText(R.id.tv_tv_push_message_content, item.getMessage());
    }
}
