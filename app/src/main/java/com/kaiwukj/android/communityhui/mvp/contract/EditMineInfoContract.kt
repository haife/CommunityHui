package com.kaiwukj.android.communityhui.mvp.contract

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


interface EditMineInfoContract {

    interface View : IView {
        fun onGetMyCollectionData(list: List<MineCollectionResult>)

        fun onGetMyAddressList(result: MyAddressResult)
    }

    interface Model : IModel {

        fun requestMyCollection(request: MineCollectionRequest): Observable<MineCollectionResult>
        //获取地址
        fun requestMyAddress(): Observable<MyAddressResult>
        //编辑地址
        fun upDateMyAddress(request: MyAddressResult): Observable<BaseStatusResult>
        //新增地址
        fun addMyAddress(request: MyAddressResult): Observable<BaseStatusResult>
    }

}
