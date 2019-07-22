package com.kaiwukj.android.communityhui.mvp.contract

import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
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
interface AppointmentContract {

    interface View : IView {
        fun onGetStaffDetailInfo(result: StaffInfoResult)
    }

    interface Model : IModel {
        //选择阿姨
        fun requestSelectStaffDetail(userId: Int): Observable<StaffListResult>
    }

}
