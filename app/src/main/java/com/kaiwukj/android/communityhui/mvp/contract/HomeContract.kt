package com.kaiwukj.android.communityhui.mvp.contract

import androidx.fragment.app.Fragment
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView


interface HomeContract {
    interface View : IView {
         fun getFragment(): Fragment
    }

    interface Model : IModel

}
