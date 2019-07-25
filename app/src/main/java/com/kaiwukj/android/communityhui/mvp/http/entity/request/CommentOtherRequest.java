package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-23
 */
public class CommentOtherRequest {
    private int noteId;
    private String commentatorId;
    private String content;


    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(String commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
