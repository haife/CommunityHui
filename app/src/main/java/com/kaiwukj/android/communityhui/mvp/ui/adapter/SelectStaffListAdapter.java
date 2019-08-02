package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc $desc
 */
public class SelectStaffListAdapter extends BaseQuickAdapter<StaffListResult, BaseViewHolder> {
    private Typeface typeFaceMedium;

    public SelectStaffListAdapter(int layoutResId, @Nullable List<StaffListResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        typeFaceMedium = Typeface.createFromAsset(mContext.getAssets(), "PingFangSC-Medium-Bold.ttf");
    }

    @Override
    protected void convert(BaseViewHolder helper, StaffListResult item) {
        ImageView ima = helper.getView(R.id.iv_house_staff_list);

        GlideArms.with(mContext).load(Api.IMG_URL + item.getAvatar()).centerCrop().into(ima);
        String staffMsg = String.format(mContext.getString(R.string.home_format_staff_message), String.valueOf(item.getWorktime()), item.getAge(), item.getNativePlace());
        helper.setText(R.id.iv_house_staff_list_name, item.getRealName()).setTypeface(typeFaceMedium).setText(R.id.iv_house_staff_list_price,
                String.format(mContext.getString(R.string.home_format_staff_price), item.getServicePrice().toString()))
                .setText(R.id.iv_house_staff_list_message, staffMsg)
                .setText(R.id.iv_house_staff_list_order_number, String.format(mContext.getString(R.string.home_format_staff_order_number), item.getOrderNum()));
    }
}
