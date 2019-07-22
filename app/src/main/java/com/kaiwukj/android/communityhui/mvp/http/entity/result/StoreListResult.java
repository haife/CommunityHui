package com.kaiwukj.android.communityhui.mvp.http.entity.result;

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
    private String storeLogoImg;
    private String storeName;
    private List<Integer> ids;
    private List<String> names;

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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
