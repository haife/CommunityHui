package com.kaiwukj.android.communityhui.mvp.contract;

import android.content.Context;

import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.mcas.mvp.IModel;
import com.kaiwukj.android.mcas.mvp.IView;

import androidx.annotation.Nullable;
import io.reactivex.Observable;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
public interface SocialCircleContract {

    interface View extends IView {
        Context getCtx();

        void finishRefresh();

        void finishLoadMore(@Nullable boolean noData);

    }

    interface Model extends IModel {

        Observable<CircleHomeResult> requestCircleHomeList(CircleHomeRequest request, boolean isRefresh);

        Observable<CircleCardResult> requestCircleCardList();

        Observable<CircleHotResult> requestCircleHotList();
    }
}
