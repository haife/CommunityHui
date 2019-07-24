package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/23
 * @desc $desc
 */
public class CircleHomeRequest {
    private int type;
    private int pages;

    public CircleHomeRequest() {
    }

    public CircleHomeRequest(int type) {
        this.type = type;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
