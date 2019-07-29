package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-24
 */
public class CircleCardDetailRequest {

    private int noteId;
    private int pageNum;

    public CircleCardDetailRequest(int noteId) {
        this.noteId = noteId;
    }

    public CircleCardDetailRequest(int noteId, int pages) {
        this.noteId = noteId;
        this.pageNum = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
