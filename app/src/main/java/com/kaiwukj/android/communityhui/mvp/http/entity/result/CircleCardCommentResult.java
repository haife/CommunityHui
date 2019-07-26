package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseListRootResult;

import java.util.List;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-27
 */
public class CircleCardCommentResult extends BaseListRootResult<List<CircleCardCommentResult>> {
    private String commentatorHeadImg;
    private int commentatorId;
    private String commentatorNickName;
    private String content;
    private String createTime;
    private int id;
    private int landlordFlag;
    private int noteId;
    private String replyHeadImg;
    private int replyId;
    private String replyNickName;
    private String updateTime;

    public String getCommentatorHeadImg() {
        return commentatorHeadImg;
    }

    public void setCommentatorHeadImg(String commentatorHeadImg) {
        this.commentatorHeadImg = commentatorHeadImg;
    }

    public int getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(int commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getCommentatorNickName() {
        return commentatorNickName;
    }

    public void setCommentatorNickName(String commentatorNickName) {
        this.commentatorNickName = commentatorNickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLandlordFlag() {
        return landlordFlag;
    }

    public void setLandlordFlag(int landlordFlag) {
        this.landlordFlag = landlordFlag;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getReplyHeadImg() {
        return replyHeadImg;
    }

    public void setReplyHeadImg(String replyHeadImg) {
        this.replyHeadImg = replyHeadImg;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyNickName() {
        return replyNickName;
    }

    public void setReplyNickName(String replyNickName) {
        this.replyNickName = replyNickName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
