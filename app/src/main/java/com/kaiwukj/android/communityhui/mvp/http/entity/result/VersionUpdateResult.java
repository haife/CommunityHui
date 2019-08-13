package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/8/12
 * @desc $desc
 */
public class VersionUpdateResult extends BaseRootResult<VersionUpdateResult> {

        private int appType;
        private String channelInfo;
        private String content;
        private String createTime;
        private int id;
        private int statusFlag;
        private int forceFlag;
        private String updateTime;
        private String versionNo;

    public int getForceFlag() {
        return forceFlag;
    }

    public void setForceFlag(int forceFlag) {
        this.forceFlag = forceFlag;
    }

    public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }

        public String getChannelInfo() {
            return channelInfo;
        }

        public void setChannelInfo(String channelInfo) {
            this.channelInfo = channelInfo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatusFlag() {
            return statusFlag;
        }

        public void setStatusFlag(int statusFlag) {
            this.statusFlag = statusFlag;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }
}
