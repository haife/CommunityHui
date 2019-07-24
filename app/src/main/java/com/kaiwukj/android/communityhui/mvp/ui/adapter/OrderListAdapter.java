package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/24
 * @desc $desc
 */
public class OrderListAdapter extends BaseQuickAdapter<OrderListResult, BaseViewHolder> {

    public OrderListAdapter(int layoutResId, @Nullable List<OrderListResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListResult item) {
        helper.setText(R.id.tv_service_order_number, item.getOrderNo())
                .setText(R.id.iv_service_order_name, item.getRealName())
                .setText(R.id.iv_service_order_message, item.getRealName())
                .setText(R.id.iv_service_order_address, item.getServiceAddress());
        ImageView iv = helper.getView(R.id.iv_service_order);
        TextView mType = helper.getView(R.id.iv_service_order_order_number);

        switch (item.getStatusFlag()) {
            case 1:
                mType.setText(mContext.getString(R.string.order_evaluate_wait));
                break;
            case 2:
                mType.setText(mContext.getString(R.string.order_evaluate_interview));
                break;
            case 3:
                mType.setText(mContext.getString(R.string.order_evaluate_contract));
                break;
            case 4:
                mType.setText(mContext.getString(R.string.order_evaluate_serving));
                break;
            case 6:
                mType.setText(mContext.getString(R.string.order_evaluate_finish));
                break;
            case 0:
                mType.setText(mContext.getString(R.string.order_evaluate_cancel));
                break;
        }
        GlideArms.with(mContext).load(Api.IMG_URL + item.getAvatar()).centerCrop().into(iv);


    }
}
