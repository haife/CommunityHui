package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult;

import java.util.List;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-24
 */
public class CircleCardDetailResult extends BaseRootResult<CircleCardDetailResult> {

    private String address;
    private int commentNum;
    private String content;
    private String createTime;
    private String headImg;
    private String nickName;
    private String statusFlag;
    private int fansFlag;
    private int id;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    private String imgs;
    private String noteType;
    private String title;
    private int type;
    private String updateTime;
    private int userId;
    private List<String> imgList;
    private List<UnoteCommentListBean> unoteCommentList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
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

    public int getFansFlag() {
        return fansFlag;
    }

    public void setFansFlag(int fansFlag) {
        this.fansFlag = fansFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<UnoteCommentListBean> getUnoteCommentList() {
        return unoteCommentList;
    }

    public void setUnoteCommentList(List<UnoteCommentListBean> unoteCommentList) {
        this.unoteCommentList = unoteCommentList;
    }

    public static class UnoteCommentListBean  {
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
}
