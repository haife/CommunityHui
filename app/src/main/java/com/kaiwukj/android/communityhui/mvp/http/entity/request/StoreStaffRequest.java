package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc $desc
 */
public class StoreStaffRequest {
    public int serviceTypeId;
    public boolean serviceHome;
    public boolean workStartTime;
    public boolean score;
    public boolean servicePrice;
    private int pages;
    private int pageSize = 10;


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public boolean isServiceHome() {
        return serviceHome;
    }

    public void setServiceHome(boolean serviceHome) {
        this.serviceHome = serviceHome;
    }

    public boolean isWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(boolean workStartTime) {
        this.workStartTime = workStartTime;
    }

    public boolean isScore() {
        return score;
    }

    public void setScore(boolean score) {
        this.score = score;
    }

    public boolean isServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(boolean servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
