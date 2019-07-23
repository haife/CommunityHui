package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-24
 */
public class CircleCardDetailRequest {

    private int noteId;


    public CircleCardDetailRequest(int noteId) {
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
