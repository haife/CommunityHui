package com.kaiwukj.android.communityhui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.lzy.ninegrid.NineGridView;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-21
 */
public class NineGridImageLoader implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        GlideArms.with(context).load(Api.IMG_URL + url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }


    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
