package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyFansListResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-29
 * 圈子主页帖子
 */
public class CirclePersonMyFansAdapter extends BaseQuickAdapter<MyFansListResult, BaseViewHolder> {


    public CirclePersonMyFansAdapter(int layoutResId, @Nullable List<MyFansListResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFansListResult item) {
        helper.setText(R.id.riv_circle_person_page_name, item.getNickName());

        QMUIRadiusImageView image = helper.getView(R.id.riv_circle_person_page_info_photo);
        GlideArms.with(mContext).load(item.getHeadImg()).circleCrop().into(image);
        if (!McaUtils.isEmpty(item.getPerSign())) {
            helper.setText(R.id.tv_circle_person_page_sign, item.getPerSign());
        } else {
            helper.setText(R.id.tv_circle_person_page_sign, "这个人太懒了，什么都没留下～");
        }
    }
}
