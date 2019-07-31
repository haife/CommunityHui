package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardCommentResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/24
 * @desc $desc
 */
public class SocialCardCommentAdapter extends BaseQuickAdapter<CircleCardCommentResult, BaseViewHolder> {

    public SocialCardCommentAdapter(int layoutResId, List<CircleCardCommentResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleCardCommentResult item) {
        QMUIRadiusImageView headIv = helper.getView(R.id.riv_card_comment_person_info_photo);
        TextView floorTv = helper.getView(R.id.tvr_card_comment_person_floor);
        floorTv.setVisibility(item.getLandlordFlag() == 1 ? View.VISIBLE : View.GONE);
        GlideArms.with(mContext).load(Api.IMG_URL + item.getCommentatorHeadImg()).into(headIv);
        helper.setText(R.id.tvr_card_comment_person_info_name, item.getCommentatorNickName())
                .setText(R.id.tv_card_comment_person_content, item.getContent())
                .setText(R.id.tv_card_comment_person_time, item.getCreateTime())
                .setText(R.id.tv_card_comment_floor, String.format(mContext.getString(R.string.card_comment_floor), helper.getAdapterPosition() + 1));
        helper.addOnClickListener(R.id.tv_card_comment_reply);
    }
}
