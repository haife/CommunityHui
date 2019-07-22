package com.kaiwukj.android.communityhui.mvp.http.entity.result;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc $desc
 */
public class BaseListRootResult<T> {
    /**
     * code : 1
     * desc : 操作成功！
     * result : {"list":[{"address":"","age":46,"avatar":"icon_20190703171945373.jpg","education":3,"evaluate":"","gender":0,"hmstoreId":1,"nation":"汉族","nativePlace":"0","orderNum":1,"realName":"测试","recommendFlag":0,"serviceHome":0,"servicePrice":"300","serviceStar":"","serviceTypeId":4,"storeemployeeId":13,"workStartTime":"2018-08-08 00:00:00","worktime":0},{"address":"","age":41,"avatar":"icon_20190703171945373.jpg","education":2,"evaluate":"自我感觉良好","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"江西景德镇","orderNum":0,"realName":"李春华","recommendFlag":0,"serviceHome":0,"servicePrice":"100","serviceStar":"","serviceTypeId":1,"storeemployeeId":1,"workStartTime":"2010-01-01 00:00:00","worktime":9},{"address":"","age":41,"avatar":"icon_20190703171945373.jpg","education":2,"evaluate":"自我感觉良好","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"江西景德镇","orderNum":0,"realName":"李春华","recommendFlag":0,"serviceHome":0,"servicePrice":"2000","serviceStar":"","serviceTypeId":2,"storeemployeeId":1,"workStartTime":"2010-01-01 00:00:00","worktime":9},{"address":"","age":41,"avatar":"icon_20190703171945373.jpg","education":2,"evaluate":"自我感觉良好","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"江西景德镇","orderNum":0,"realName":"李春华","recommendFlag":0,"serviceHome":0,"servicePrice":"500","serviceStar":"","serviceTypeId":3,"storeemployeeId":1,"workStartTime":"2010-01-01 00:00:00","worktime":9},{"address":"","age":40,"avatar":"icon_20190703171945373.jpg","education":3,"evaluate":"很好","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"江西吉安","orderNum":0,"realName":"胡·少华","recommendFlag":0,"serviceHome":0,"servicePrice":"400","serviceStar":"","serviceTypeId":2,"storeemployeeId":2,"workStartTime":"2015-12-01 00:00:00","worktime":3},{"address":"","age":40,"avatar":"icon_20190703171945373.jpg","education":3,"evaluate":"很好","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"江西吉安","orderNum":0,"realName":"胡·少华","recommendFlag":0,"serviceHome":0,"servicePrice":"6000","serviceStar":"","serviceTypeId":4,"storeemployeeId":2,"workStartTime":"2015-12-01 00:00:00","worktime":3},{"address":"","age":44,"avatar":"","education":1,"evaluate":"","gender":0,"hmstoreId":2,"nation":"汉族","nativePlace":"江西赣州","orderNum":0,"realName":"刘春花","recommendFlag":0,"serviceHome":0,"servicePrice":"","serviceStar":"","serviceTypeId":0,"storeemployeeId":9,"workStartTime":"2017-09-05 00:00:00","worktime":1},{"address":"","age":45,"avatar":"icon_20190703171945373.jpg","education":1,"evaluate":"","gender":0,"hmstoreId":1,"nation":"汉族","nativePlace":"江西南昌","orderNum":0,"realName":"张秀月","recommendFlag":0,"serviceHome":0,"servicePrice":"30000","serviceStar":"","serviceTypeId":3,"storeemployeeId":12,"workStartTime":"2017-09-05 00:00:00","worktime":1},{"address":"","age":28,"avatar":"http://qnzhsq.kaiwumace.com/1563352847847","education":6,"evaluate":"","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"360000","orderNum":0,"realName":"龙3","recommendFlag":0,"serviceHome":0,"servicePrice":"30000","serviceStar":"","serviceTypeId":3,"storeemployeeId":17,"workStartTime":"2013-07-01 00:00:00","worktime":6},{"address":"","age":28,"avatar":"http://qnzhsq.kaiwumace.com/1563352847847","education":6,"evaluate":"","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"360000","orderNum":0,"realName":"龙3","recommendFlag":0,"serviceHome":0,"servicePrice":"50000","serviceStar":"","serviceTypeId":1,"storeemployeeId":17,"workStartTime":"2013-07-01 00:00:00","worktime":6},{"address":"","age":28,"avatar":"http://qnzhsq.kaiwumace.com/1563352847847","education":6,"evaluate":"","gender":0,"hmstoreId":3,"nation":"汉族","nativePlace":"360000","orderNum":0,"realName":"龙3","recommendFlag":0,"serviceHome":0,"servicePrice":"30000","serviceStar":"","serviceTypeId":3,"storeemployeeId":18,"workStartTime":"2013-07-01 00:00:00","worktime":6},{"address":"","age":0,"avatar":"/common/getQiNiuToken/icon_20190718164917114.jpg","education":3,"evaluate":"","gender":0,"hmstoreId":1,"nation":"汉族","nativePlace":"0","orderNum":0,"realName":"测试2号","recommendFlag":0,"serviceHome":0,"servicePrice":"5000","serviceStar":"","serviceTypeId":1,"storeemployeeId":19,"workStartTime":"2009-07-10 00:00:00","worktime":10},{"address":"","age":0,"avatar":"/common/getQiNiuToken/icon_20190718164917114.jpg","education":3,"evaluate":"","gender":0,"hmstoreId":1,"nation":"汉族","nativePlace":"0","orderNum":0,"realName":"测试3号","recommendFlag":0,"serviceHome":0,"servicePrice":"5000","serviceStar":"","serviceTypeId":1,"storeemployeeId":20,"workStartTime":"2019-07-10 00:00:00","worktime":0},{"address":"","age":0,"avatar":"/common/getQiNiuToken/icon_20190718170041693.jpg","education":2,"evaluate":"","gender":0,"hmstoreId":1,"nation":"汉族","nativePlace":"0","orderNum":0,"realName":"测试4号","recommendFlag":0,"serviceHome":0,"servicePrice":"2000","serviceStar":"","serviceTypeId":1,"storeemployeeId":21,"workStartTime":"2019-07-01 00:00:00","worktime":0},{"address":"","age":0,"avatar":"icon_20190718180510563.jpg","education":1,"evaluate":"","gender":0,"hmstoreId":1,"nation":"汉族","nativePlace":"0","orderNum":0,"realName":"测试5号","recommendFlag":0,"serviceHome":0,"servicePrice":"5000","serviceStar":"","serviceTypeId":1,"storeemployeeId":22,"workStartTime":"2019-07-01 00:00:00","worktime":0}],"pageNum":1,"pageSize":20,"pages":1,"total":15}
     */

    private String code;
    private String desc;
    private ResultBean<T> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean<T> getResult() {
        return result;
    }

    public void setResult(ResultBean<T> result) {
        this.result = result;
    }

    public static class ResultBean<T> {
        private int pageNum;
        private int pageSize;
        private int pages;
        private int total;
        private T list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public T getList() {
            return list;
        }

        public void setList(T list) {
            this.list = list;
        }


    }
}
