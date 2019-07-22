package com.kaiwukj.android.communityhui.mvp.contract

import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreStaffRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
interface HouseKeepContract {

    interface View : IView {
        fun onGetServiceList(result: List<HomeServiceEntity>)
        fun onSelectStaffList(result: List<StaffListResult>)
    }

    interface Model : IModel {
        fun requestServiceList(): Observable<HomeServiceEntity>
        //选择阿姨
        fun requestSelectStaff(request: StoreStaffRequest): Observable<StaffListResult>
    }

}
