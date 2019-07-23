package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-23
 */
public class CommentOtherRequest {
    private int noteId;
    private int commentatorId;
    private int replyId;
    private String content;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(int commentatorId) {
        this.commentatorId = commentatorId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
