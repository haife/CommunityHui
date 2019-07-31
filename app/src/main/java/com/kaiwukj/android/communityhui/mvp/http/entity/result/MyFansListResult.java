package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseListRootResult;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/29
 * @desc $desc
 */
public class MyFansListResult extends BaseListRootResult<List<MyFansListResult>> {

    private String headImg;
    private String nickName;
    private int fansId;
    private int focusedUserId;
    private String perSign;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getFansId() {
        return fansId;
    }

    public void setFansId(int fansId) {
        this.fansId = fansId;
    }

    public int getFocusedUserId() {
        return focusedUserId;
    }

    public void setFocusedUserId(int focusedUserId) {
        this.focusedUserId = focusedUserId;
    }

    public String getPerSign() {
        return perSign;
    }

    public void setPerSign(String perSign) {
        this.perSign = perSign;
    }
}
