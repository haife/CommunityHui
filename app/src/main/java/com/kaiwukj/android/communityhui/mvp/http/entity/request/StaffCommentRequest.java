package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc 首页阿姨的评价
 */
public class StaffCommentRequest  {

    public StaffCommentRequest(int storeemployeeId) {
        this.storeemployeeId = storeemployeeId;
    }

    private int storeemployeeId;

    public int getStoreemployeeId() {
        return storeemployeeId;
    }

    public void setStoreemployeeId(int storeemployeeId) {
        this.storeemployeeId = storeemployeeId;
    }
}
