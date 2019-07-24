package com.kaiwukj.android.communityhui.mvp.http.entity.result;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-24
 */
public class SocialUserHomePageRequest {

    @Nullable
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
