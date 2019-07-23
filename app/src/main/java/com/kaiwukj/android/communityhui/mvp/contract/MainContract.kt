package com.kaiwukj.android.communityhui.mvp.contract

import androidx.appcompat.app.AppCompatActivity
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView


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
    }

    interface Model : IModel {
        //fun getMineInfoData(): Observable<MineUserInfoResult>
    }

}
