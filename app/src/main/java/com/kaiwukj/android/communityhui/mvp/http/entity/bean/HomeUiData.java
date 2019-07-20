package com.kaiwukj.android.communityhui.mvp.http.entity.bean;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;

import java.util.List;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/20
 * @desc $desc
 */
public class HomeUiData {

    private List<HomeServiceEntity> homeServiceList;

    public List<HomeServiceEntity> getHomeServiceList() {
        return homeServiceList;
    }

    public void setHomeServiceList(List<HomeServiceEntity> homeServiceList) {
        this.homeServiceList = homeServiceList;
    }
}
