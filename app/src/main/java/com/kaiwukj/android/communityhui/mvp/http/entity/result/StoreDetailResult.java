package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/23
 * @desc $desc
 */
public class StoreDetailResult extends BaseRootResult<StoreDetailResult> {

        private String address;
        private String agreementImg;
        private String city;
        private int communityManagerId;
        private String district;
        private int employeeNum;
        private String extractConfig;
        private int favoriteFlag;
        private int id;
        private String idCardBackImg;
        private String idCardFrontImg;
        private String idCardNo;
        private String licenseImg;
        private String masterName;
        private String phoneNo;
        private String province;
        private String serviceTypeIds;
        private int statusFlag;
        private String storeLogoImg;
        private String storeName;
        private int storeType;
        private int storemanagerId;
        private String uscc;
        private List<StoreSortResponseListBean> storeSortResponseList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAgreementImg() {
            return agreementImg;
        }

        public void setAgreementImg(String agreementImg) {
            this.agreementImg = agreementImg;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCommunityManagerId() {
            return communityManagerId;
        }

        public void setCommunityManagerId(int communityManagerId) {
            this.communityManagerId = communityManagerId;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getEmployeeNum() {
            return employeeNum;
        }

        public void setEmployeeNum(int employeeNum) {
            this.employeeNum = employeeNum;
        }

        public String getExtractConfig() {
            return extractConfig;
        }

        public void setExtractConfig(String extractConfig) {
            this.extractConfig = extractConfig;
        }

        public int getFavoriteFlag() {
            return favoriteFlag;
        }

        public void setFavoriteFlag(int favoriteFlag) {
            this.favoriteFlag = favoriteFlag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCardBackImg() {
            return idCardBackImg;
        }

        public void setIdCardBackImg(String idCardBackImg) {
            this.idCardBackImg = idCardBackImg;
        }

        public String getIdCardFrontImg() {
            return idCardFrontImg;
        }

        public void setIdCardFrontImg(String idCardFrontImg) {
            this.idCardFrontImg = idCardFrontImg;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getLicenseImg() {
            return licenseImg;
        }

        public void setLicenseImg(String licenseImg) {
            this.licenseImg = licenseImg;
        }

        public String getMasterName() {
            return masterName;
        }

        public void setMasterName(String masterName) {
            this.masterName = masterName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getServiceTypeIds() {
            return serviceTypeIds;
        }

        public void setServiceTypeIds(String serviceTypeIds) {
            this.serviceTypeIds = serviceTypeIds;
        }

        public int getStatusFlag() {
            return statusFlag;
        }

        public void setStatusFlag(int statusFlag) {
            this.statusFlag = statusFlag;
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

        public int getStoreType() {
            return storeType;
        }

        public void setStoreType(int storeType) {
            this.storeType = storeType;
        }

        public int getStoremanagerId() {
            return storemanagerId;
        }

        public void setStoremanagerId(int storemanagerId) {
            this.storemanagerId = storemanagerId;
        }

        public String getUscc() {
            return uscc;
        }

        public void setUscc(String uscc) {
            this.uscc = uscc;
        }

        public List<StoreSortResponseListBean> getStoreSortResponseList() {
            return storeSortResponseList;
        }

        public void setStoreSortResponseList(List<StoreSortResponseListBean> storeSortResponseList) {
            this.storeSortResponseList = storeSortResponseList;
        }

        public static class StoreSortResponseListBean {
            /**
             * allItem : 0
             * recommendItem : 0
             * serviceName : 月嫂
             * serviceTypeId : 1
             */

            private int allItem;
            private int recommendItem;
            private String serviceName;
            private int serviceTypeId;

            public int getAllItem() {
                return allItem;
            }

            public void setAllItem(int allItem) {
                this.allItem = allItem;
            }

            public int getRecommendItem() {
                return recommendItem;
            }

            public void setRecommendItem(int recommendItem) {
                this.recommendItem = recommendItem;
            }

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
