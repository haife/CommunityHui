package com.kaiwukj.android.communityhui.mvp.ui.adapter;


import android.content.Context;
import android.graphics.Rect;

import com.kaiwukj.android.communityhui.mvp.browse.JBrowseImgActivity;
import com.kaiwukj.android.communityhui.mvp.browse.JPhotosInfos;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.NineGridViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String[] mPaths;

    public NineGridIvAdapter(Context context, List<ImageInfo> imageInfo) {
        super(context, imageInfo);
        mPaths = new String[imageInfo.size()];
        for (int i = 0; i < imageInfo.size(); i++) {
            mPaths[i] = imageInfo.get(i).bigImageUrl;
        }

    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<ImageInfo> imageInfo) {
        startBrowse(index, imageInfo,nineGridView);
    }


    private void startBrowse(int pos, List<ImageInfo> imageInfo,NineGridView nineGridView) {
        ArrayList<JPhotosInfos> infos = new ArrayList<>();
        for (int i = 0; i < imageInfo.size(); i++) {
            Rect rect = new Rect();
            rect.left = 32;
            rect.bottom = nineGridView.getBottom();
            rect.top = nineGridView.getTop();
            rect.right = McaUtils.getScreenWidth(context)-32;
            JPhotosInfos photosInfos = new JPhotosInfos();
            infos.add(photosInfos.build(rect));
            infos.add(photosInfos);
        }


        JBrowseImgActivity.start(context, new ArrayList<>(Arrays.asList(mPaths)), pos, infos);
    }


}
