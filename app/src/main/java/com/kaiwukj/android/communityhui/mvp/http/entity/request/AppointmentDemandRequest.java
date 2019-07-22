package com.kaiwukj.android.communityhui.mvp.http.entity.request;

import java.util.Date;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-23
 */
public class AppointmentDemandRequest {
    private int serviceTypeId;
    private int addressId;
    private Date planBeginTime;
    private int serviceLength;
    private int hmstoreId;
    private int storeemployeeId;
    private String description;

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public int getServiceLength() {
        return serviceLength;
    }

    public void setServiceLength(int serviceLength) {
        this.serviceLength = serviceLength;
    }

    public int getHmstoreId() {
        return hmstoreId;
    }

    public void setHmstoreId(int hmstoreId) {
        this.hmstoreId = hmstoreId;
    }

    public int getStoreemployeeId() {
        return storeemployeeId;
    }

    public void setStoreemployeeId(int storeemployeeId) {
        this.storeemployeeId = storeemployeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
