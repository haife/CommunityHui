package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/8/12
 * @desc $desc
 */
public class VersionUpdateRequest {

    public VersionUpdateRequest(String versionNo) {
        this.versionNo = versionNo;
        this.appType = 4;
    }

    //版本号不能为空"
    private String versionNo;
    /**
     * APP类型，1、家政门店端，2、家政技工端，3、社区经理人端，4、社区端',
     */
    private Integer appType;

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
}
