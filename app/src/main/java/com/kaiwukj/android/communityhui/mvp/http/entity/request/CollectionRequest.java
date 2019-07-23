package com.kaiwukj.android.communityhui.mvp.http.entity.request;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/23
 * @desc 添加收藏
 */
public class CollectionRequest {
    private int favoriteId;
    private int type;

    public CollectionRequest(int favoriteId, int type) {
        this.favoriteId = favoriteId;
        this.type = type;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
