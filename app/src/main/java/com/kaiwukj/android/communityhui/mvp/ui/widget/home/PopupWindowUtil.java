package com.kaiwukj.android.communityhui.mvp.ui.widget.home;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kaiwukj.android.communityhui.R;


public class PopupWindowUtil {
    public static CustomPopupWindow createImageDialog(Activity activity, String url) {
        CustomPopupWindow customPopupWindow = new CustomPopupWindow.Builder(activity)
                .setAnimationStyle(R.anim.pop_enter_anim)
                .setFouse(true)
                .setOutSideCancel(false)
                .setwidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setheight(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentView(R.layout.dialog_photo)
                .builder();
        ImageView imageView = (ImageView) customPopupWindow.getItemView(R.id.iv_image);
        Glide.with(activity).load(url).into(imageView);
        imageView.setOnClickListener(view -> customPopupWindow.dismiss());
        return customPopupWindow;
    }

}
