package com.kaiwukj.android.communityhui.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import com.youth.banner.loader.ImageLoader

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 * banner image loader
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (imageView != null) {
            GlideArms.with(context!!.applicationContext)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
        }

    }

}
