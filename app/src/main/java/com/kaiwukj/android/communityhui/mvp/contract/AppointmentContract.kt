package com.kaiwukj.android.communityhui.mvp.contract

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.AppointmentDemandRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
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
        fun onGetStaffCommentInfo(result: StaffCommentResult)
        fun onGetMyAddressList(result: MyAddressResult)
    }

    interface Model : IModel {
        //阿姨详情
        fun requestSelectStaffDetail(userId: Int): Observable<StaffInfoResult>

        //获取用户评价
        fun requestUserComment(userId: Int): Observable<StaffCommentResult>

        //获取地址
        fun requestMyAddress(): Observable<MyAddressResult>

        //提交订单
        fun requestAppointmentDate(request: AppointmentDemandRequest): Observable<BaseStatusResult>
    }

}
