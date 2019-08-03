package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/24
 * @desc $desc
 */
public class OrderListRequest {
    //3:待服务 4：服务中 5：已完结，不传值即为查看所有订单
    private String statusFlag;
    private int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public OrderListRequest(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public OrderListRequest(String statusFlag, int pageNum) {
        this.statusFlag = statusFlag;
        this.pageNum = pageNum;
    }
}
