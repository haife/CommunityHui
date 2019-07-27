package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

import java.io.Serializable;
import java.util.List;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-22
 */
public class MyAddressResult extends BaseRootResult<List<MyAddressResult>> implements Serializable {
    private String area;
    private String address;
    private String phone;
    private String name;
    private int communityId;

    //如果是预约过来的需要传递
    private boolean isFromToAppointment = false;

    public boolean isFromToAppointment() {
        return isFromToAppointment;
    }

    public void setFromToAppointment(boolean fromToAppointment) {
        isFromToAppointment = fromToAppointment;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }
}
