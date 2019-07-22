package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseListRootResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc 首页阿姨推荐阿姨信息
 */
public class StaffListResult extends BaseListRootResult<List<StaffListResult>> {

    private int age;
    private String avatar;
    private double avgScore;
    private int education;
    private String evaluate;
    private int hmstoreId;
    private String nation;
    private String nativePlace;
    private int orderNum;
    private String realName;
    private String serviceName;
    private int serviceHome;
    private BigDecimal servicePrice;
    private int serviceTypeId;
    private int storeemployeeId;
    private int worktime;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }


    public int getServiceHome() {
        return serviceHome;
    }

    public void setServiceHome(int serviceHome) {
        this.serviceHome = serviceHome;
    }

    public int getHmstoreId() {
        return hmstoreId;
    }

    public void setHmstoreId(int hmstoreId) {
        this.hmstoreId = hmstoreId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
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
