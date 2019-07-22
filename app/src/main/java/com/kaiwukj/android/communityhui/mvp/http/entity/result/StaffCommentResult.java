package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc 首页阿姨的评价
 */
public class StaffCommentResult extends BaseRootResult<List<StaffCommentResult>> {

    private int createBy;
    private String content;
    private String headImg;
    private String nickName;

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
}
