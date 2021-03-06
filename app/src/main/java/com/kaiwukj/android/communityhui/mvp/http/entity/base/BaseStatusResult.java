package com.kaiwukj.android.communityhui.mvp.http.entity.base;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-23
 */
public class BaseStatusResult {
    private String error ;
    private String code;
    private String desc;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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
}
