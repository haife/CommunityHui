package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-25
 */
public class MyAddressAdapter extends BaseQuickAdapter<MyAddressResult, BaseViewHolder> {

    public MyAddressAdapter(int layoutResId, @Nullable List<MyAddressResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyAddressResult item) {
        helper.setText(R.id.tv_mine_address_detail, item.getArea() + "\t" + item.getAddress()).setText(R.id.tv_mine_address_name, item.getName())
                .setText(R.id.tv_mine_address_phone, item.getPhone());
        helper.addOnClickListener(R.id.iv_mine_address_edit);
    }
}
