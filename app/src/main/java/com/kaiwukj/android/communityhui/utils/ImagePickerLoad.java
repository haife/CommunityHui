package com.kaiwukj.android.communityhui.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;
/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-21
 *
 */
public class ImagePickerLoad implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideArms.with(activity).load(Uri.fromFile(new File(path)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideArms.with(activity).load(Uri.fromFile(new File(path)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
