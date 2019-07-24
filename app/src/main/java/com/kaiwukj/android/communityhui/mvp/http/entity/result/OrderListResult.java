package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/24
 * @desc $desc
 */
public class OrderListResult extends BaseRootResult<List<OrderListResult>> implements Serializable {
    private String avatar;
    private String avgScore;
    private String createTime;
    private String description;
    private int hmStoreId;
    private String interviewTime;
    private String nativePlace;
    private int orderId;
    private String orderNo;
    private String phoneNo;
    private String realName;
    private String serviceAddress;
    private int serviceLength;
    private int serviceTypeId;
    private String serviceTypeName;
    private String serviceTypeUnit;
    private int statusFlag;
    private String storeName;
    private int storeemployeeId;
    private int worktime;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHmStoreId() {
        return hmStoreId;
    }

    public void setHmStoreId(int hmStoreId) {
        this.hmStoreId = hmStoreId;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public int getServiceLength() {
        return serviceLength;
    }

    public void setServiceLength(int serviceLength) {
        this.serviceLength = serviceLength;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getServiceTypeUnit() {
        return serviceTypeUnit;
    }

    public void setServiceTypeUnit(String serviceTypeUnit) {
        this.serviceTypeUnit = serviceTypeUnit;
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreemployeeId() {
        return storeemployeeId;
    }

    public void setStoreemployeeId(int storeemployeeId) {
        this.storeemployeeId = storeemployeeId;
    }

    public int getWorktime() {
        return worktime;
    }

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }
}
