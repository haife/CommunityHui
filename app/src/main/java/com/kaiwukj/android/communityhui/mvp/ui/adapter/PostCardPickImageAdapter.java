package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-21
 * 发帖子选取图片控件
 */
public class PostCardPickImageAdapter extends BaseQuickAdapter<ImageItem, BaseViewHolder> {
    private Context mContext;

    public PostCardPickImageAdapter(int layoutResId, @Nullable List<ImageItem> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageItem item) {
        if (item.path!=null){
            ImageView imageView = helper.getView(R.id.iv_post_card_pick_image);
            GlideArms.with(mContext).load(item.path).centerCrop().into(imageView);

        }



    }
}
