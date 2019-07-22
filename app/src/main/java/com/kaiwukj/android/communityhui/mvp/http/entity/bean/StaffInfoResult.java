package com.kaiwukj.android.communityhui.mvp.http.entity.bean;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc $desc
 */
public class StaffInfoResult {

        private int age;
        private String avatar;
        private int avgScore;
        private String evaluate;
        private int hmstoreId;
        private String nation;
        private String nativePlace;
        private String realName;
        private int serviceHome;
        private int servicePrice;
        private String storeName;
        private int storeemployeeId;
        private int worktime;
        private List<EmpCommentListBean> empCommentList;
        private List<?> empTagList;
        private List<EmpTypeListBean> empTypeList;
        private List<ImgListBean> imgList;

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

        public int getAvgScore() {
            return avgScore;
        }

        public void setAvgScore(int avgScore) {
            this.avgScore = avgScore;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getServiceHome() {
            return serviceHome;
        }

        public void setServiceHome(int serviceHome) {
            this.serviceHome = serviceHome;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
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

        public List<EmpCommentListBean> getEmpCommentList() {
            return empCommentList;
        }

        public void setEmpCommentList(List<EmpCommentListBean> empCommentList) {
            this.empCommentList = empCommentList;
        }

        public List<?> getEmpTagList() {
            return empTagList;
        }

        public void setEmpTagList(List<?> empTagList) {
            this.empTagList = empTagList;
        }

        public List<EmpTypeListBean> getEmpTypeList() {
            return empTypeList;
        }

        public void setEmpTypeList(List<EmpTypeListBean> empTypeList) {
            this.empTypeList = empTypeList;
        }

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class EmpCommentListBean {

            private String content;
            private int createBy;
            private String headImg;
            private String nickName;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreateBy() {
                return createBy;
            }

            public void setCreateBy(int createBy) {
                this.createBy = createBy;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }

        public static class EmpTypeListBean {

            private int priceYuan;
            private int servicePrice;
            private String serviceType;
            private int serviceTypeId;
            private String serviceUnit;
            private int statusFlag;
            private int storeemployeeId;

            public int getPriceYuan() {
                return priceYuan;
            }

            public void setPriceYuan(int priceYuan) {
                this.priceYuan = priceYuan;
            }

            public int getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(int servicePrice) {
                this.servicePrice = servicePrice;
            }

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public int getServiceTypeId() {
                return serviceTypeId;
            }

            public void setServiceTypeId(int serviceTypeId) {
                this.serviceTypeId = serviceTypeId;
            }

            public String getServiceUnit() {
                return serviceUnit;
            }

            public void setServiceUnit(String serviceUnit) {
                this.serviceUnit = serviceUnit;
            }

            public int getStatusFlag() {
                return statusFlag;
            }

            public void setStatusFlag(int statusFlag) {
                this.statusFlag = statusFlag;
            }

            public int getStoreemployeeId() {
                return storeemployeeId;
            }

            public void setStoreemployeeId(int storeemployeeId) {
                this.storeemployeeId = storeemployeeId;
            }
        }

        public static class ImgListBean {
            /**
             * img : icon_20190703171945373.jpg
             * imgType : 1
             */

            private String img;
            private int imgType;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getImgType() {
                return imgType;
            }

            public void setImgType(int imgType) {
                this.imgType = imgType;
            }
        }
    }
