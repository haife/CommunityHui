package com.kaiwukj.android.communityhui.mvp.ui.adapter;


import android.app.Activity;
import android.content.Context;

import com.kaiwukj.android.communityhui.mvp.ui.widget.home.PopupWindowUtil;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.NineGridViewAdapter;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/26
 * @desc $desc
 */
public class NineGridIvAdapter extends NineGridViewAdapter {

    private Activity mActivity;
    public NineGridIvAdapter(Context context, List<ImageInfo> imageInfo, Activity activity) {
        super(context, imageInfo);
        mActivity = activity;
    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<ImageInfo> imageInfo) {
        PopupWindowUtil.createImageDialog(mActivity, imageInfo.get(index).bigImageUrl);
    }
}
