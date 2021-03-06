package com.kaiwukj.android.communityhui.mvp.contract

import androidx.fragment.app.Fragment
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.VersionUpdateResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


interface HomeContract {
    interface View : IView {
        fun getFragment(): Fragment
        fun onResponseError()

    }

    interface Model : IModel {
        fun requestServiceList(): Observable<HomeServiceEntity>
        fun requestStoreRecommend(recommendFlg: StoreListRequest, isRefresh: Boolean): Observable<StoreListResult>
        fun requestStaffRecommend(request: StoreListRequest, isRefresh: Boolean): Observable<StaffListResult>

    }

}
