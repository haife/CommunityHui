package com.kaiwukj.android.communityhui.mvp.listener;


import com.kaiwukj.android.communityhui.mvp.http.entity.bean.SubImageBean;

import java.util.List;

public interface OnSubDataUpdateListener {
    void onUpdateUrls(List<SubImageBean> urls);
}