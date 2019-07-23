package com.kaiwukj.android.communityhui.mvp.http.entity.request;

import java.util.List;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-23
 * 发帖实体类
 */
public class PostCardRequest {
    private String title;
    private Integer Integer;
    private String content;
    private String address;
    private List<String> imgList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.lang.Integer getInteger() {
        return Integer;
    }

    public void setInteger(java.lang.Integer integer) {
        Integer = integer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
