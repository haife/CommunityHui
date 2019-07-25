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
 * @time 2019/7/20
 * @desc $desc
 */
public class HomeServiceEntity extends BaseRootResult<List<HomeServiceEntity>>implements Serializable {
    private String dicValue;
    private int id;
    private String img;

    public String getDicValue() {
        return dicValue;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
