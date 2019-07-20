package com.kaiwukj.android.communityhui.mvp.http.entity.base;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/20
 * @desc $desc
 */
public class BaseRootResult<T> {


    /**
     * code : string
     * desc : string
     * result : {}
     */

    private String code;
    private String desc;
    private T result;

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


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
