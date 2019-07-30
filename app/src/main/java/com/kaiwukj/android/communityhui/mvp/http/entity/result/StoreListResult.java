package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseListRootResult;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/18
 * @desc https://github.com/VictorAlbertos/RxCache/issues/73
 */
public class StoreListResult extends BaseListRootResult<List<StoreListResult>> {
    private String address;
    private int id;
    private int orderNum;
    private String serviceTypeIds;
    private String storeLogoImg;
    private String storeName;
    private List<StoreSortResponseListBean> storeSortResponseList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getServiceTypeIds() {
        return serviceTypeIds;
    }

    public void setServiceTypeIds(String serviceTypeIds) {
        this.serviceTypeIds = serviceTypeIds;
    }

    public String getStoreLogoImg() {
        return storeLogoImg;
    }

    public void setStoreLogoImg(String storeLogoImg) {
        this.storeLogoImg = storeLogoImg;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<StoreSortResponseListBean> getStoreSortResponseList() {
        return storeSortResponseList;
    }

    public void setStoreSortResponseList(List<StoreSortResponseListBean> storeSortResponseList) {
        this.storeSortResponseList = storeSortResponseList;
    }

    public static class StoreSortResponseListBean {


        private String serviceName;
        private int serviceTypeId;


        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public int getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(int serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }
    }
}
