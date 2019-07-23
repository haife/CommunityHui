package com.kaiwukj.android.communityhui.mvp.contract

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc
 */
interface MineContract {
    interface View : IView {
        fun onGetMineInfo(result: MineUserInfoResult)
        fun onGetOtherHomePageData(result: SocialUserHomePageResult)
    }

    interface Model : IModel {
        fun requestMineInfoData(): Observable<MineUserInfoResult>

        fun requestSocialHomePage(request: SocialUserHomePageRequest): Observable<SocialUserHomePageResult>

        fun updateMineInfoData(request: MineUserInfoResult): Observable<BaseStatusResult>
    }

}
