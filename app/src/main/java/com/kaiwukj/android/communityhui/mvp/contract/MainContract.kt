package com.kaiwukj.android.communityhui.mvp.contract

import androidx.appcompat.app.AppCompatActivity
import com.kaiwukj.android.communityhui.mvp.http.entity.request.VersionUpdateRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.VersionUpdateResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
interface MainContract {
    interface View : IView {
        fun getActivity(): AppCompatActivity
        fun initWidget()
        fun onVersionUpdateResult(result: VersionUpdateResult)
    }

    interface Model : IModel {
        fun requestVersionUpdate(request: VersionUpdateRequest): Observable<VersionUpdateResult>
    }

}
