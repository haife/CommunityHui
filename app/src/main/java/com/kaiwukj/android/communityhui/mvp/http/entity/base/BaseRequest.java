package com.kaiwukj.android.communityhui.mvp.http.entity.base;

import java.io.Serializable;

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  
 */
public class BaseRequest<T> implements Serializable {
    private T param;

    public BaseRequest(T param) {
        this.param = param;
    }

}
