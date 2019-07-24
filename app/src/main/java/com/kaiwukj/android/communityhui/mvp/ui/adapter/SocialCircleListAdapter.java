package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.kaiwukj.android.mcas.utils.McaUtils;

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
                .setText(R.id.tv_circle_type_tags, item.getNoteType())
                .setText(R.id.tv_circle_comment_number, String.valueOf(item.getCommentNum())).setText(R.id.tv_circle_comment_hint, mContext.getString(R.string.social_circle_comment_hint));
        TextView typeTv = helper.getView(R.id.tv_circle_type_tags);
        typeTv.setVisibility(McaUtils.isEmpty(item.getNoteType()) ? View.GONE : View.VISIBLE);
        ImageView iv = helper.getView(R.id.qriv_circle_head_photo);
        ImageView qriv_circle_image = helper.getView(R.id.qriv_circle_image);
        GlideArms.with(mContext).load(Api.IMG_URL + item.getHeadImg()).circleCrop().into(iv);
        if (item.getImgList() != null && item.getImgList().size() > 0) {
            GlideArms.with(mContext).load(Api.IMG_URL + item.getImgList().get(0)).centerCrop().into(qriv_circle_image);
            qriv_circle_image.setVisibility(View.VISIBLE);
        } else {
            qriv_circle_image.setVisibility(View.GONE);
        }
    }
}
