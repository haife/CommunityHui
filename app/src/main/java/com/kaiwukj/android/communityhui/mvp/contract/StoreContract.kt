package com.kaiwukj.android.communityhui.mvp.contract

import android.content.Context
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 门店模块
 */


interface  StoreContract {
    interface View : IView {
        fun getContextView(): Context?
        fun onRefreshFinish()
        fun onLoadMoreFinish()
        fun onGetStoreRecommend(list: StoreListResult)
        fun onGetStoreDetail(detailResult: StoreDetailResult)
    }

    interface Model : IModel {
        fun requestAllStoreRecommend(recommendFlg: StoreListRequest): Observable<StoreListResult>
        fun requestStoreDetail(id: Int): Observable<BaseRootResult<StoreDetailResult>>
        //添加收藏
        fun requestAddCollection(request: CollectionRequest): Observable<BaseStatusResult>

        //取消收藏
        fun requestMoveCollection(id: Int): Observable<BaseStatusResult>
    }

}
