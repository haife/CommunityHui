package com.kaiwukj.android.communityhui.mvp.contract

import androidx.fragment.app.Fragment
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


interface HomeContract {
    interface View : IView {
        fun getFragment(): Fragment
        fun onGetServiceList(result: List<HomeServiceEntity>)
        fun onGetStoreRecommend()
        fun onGetStaffRecommend()
    }

    interface Model : IModel {
        fun requestServiceList(): Observable<HomeServiceEntity>
        fun requestStoreRecommend(phone: String): Observable<LoginVerifyCodeResult>
//        fun requestStaffRecommend(phone: String): Observable<LoginVerifyCodeResult>
    }

}
