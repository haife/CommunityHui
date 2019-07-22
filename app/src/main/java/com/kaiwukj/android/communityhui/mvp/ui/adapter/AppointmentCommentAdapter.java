package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-22
 */
public class AppointmentCommentAdapter extends BaseQuickAdapter<StaffCommentResult, BaseViewHolder> {

    public AppointmentCommentAdapter(int layoutResId, @Nullable List<StaffCommentResult> data, Context context) {
        super(layoutResId, data);
        context = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, StaffCommentResult item) {
        QMUIRadiusImageView headIv = helper.getView(R.id.riv_comment_person_info_photo);
        GlideArms.with(mContext).load(Api.IMG_URL + item.getHeadImg()).circleCrop().into(headIv);
        helper.setText(R.id.tv_rcomment_person_info_name,item.getNickName()).setText(R.id.tv_comment_person_content,item.getContent());
    }
}
