package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;

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
public class SocialCircleListAdapter extends BaseQuickAdapter<CircleHomeResult, BaseViewHolder> {

    public SocialCircleListAdapter(int layoutResId, @Nullable List<CircleHomeResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleHomeResult item) {
        helper.setText(R.id.tv_circle_nick_name, item.getNickName()).setText(R.id.tv_circle_publish_time, item.getCreateTime())
                .setText(R.id.tv_circle_message_title, item.getTitle()).setText(R.id.tv_circle_message_content, item.getContent())
                .setText(R.id.tv_circle_comment_number, String.valueOf(item.getCommentNum())).setText(R.id.tv_circle_comment_hint, mContext.getString(R.string.social_circle_comment_hint));
        ImageView iv = helper.getView(R.id.qriv_circle_head_photo);
        ImageView qriv_circle_image = helper.getView(R.id.qriv_circle_image);
        //GlideArms.with(mContext).load(Api.IMG_URL + item.getHeadImg()).centerCrop().into(iv);
        if (item.getImgList() != null && item.getImgList().size() > 0) {
         //   GlideArms.with(mContext).load(Api.IMG_URL + item.getImgList().get(0)).centerCrop().into(qriv_circle_image);
        }
    }
}
