package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-24
 */
public class SocialUserHomePageResult extends BaseRootResult<SocialUserHomePageResult> {


    /**
     * fansCount : 2
     * focusedCount : 0
     * headImg : icon_20190617103557330.jpg
     * id : 1
     * nickName : 牛逼布拉斯
     * noteCount : 10
     * perSign :
     * replyCount : 4
     */

    private int fansCount;
    private int focusedCount;
    private String headImg;
    private int id;
    private String nickName;
    private int noteCount;
    private String perSign;
    private String hxName;
    private int replyCount;


    public String getHxName() {
        return hxName;
    }

    public void setHxName(String hxName) {
        this.hxName = hxName;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFocusedCount() {
        return focusedCount;
    }

    public void setFocusedCount(int focusedCount) {
        this.focusedCount = focusedCount;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }

    public String getPerSign() {
        return perSign;
    }

    public void setPerSign(String perSign) {
        this.perSign = perSign;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
}
