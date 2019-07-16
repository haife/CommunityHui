package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.AppointmentActivity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentDemandFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentPersonInfoFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentResultFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Component


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
@ActivityScope
@Component(modules = [AppointmentModule::class], dependencies = [AppComponent::class])
interface AppointmentComponent {
    fun inject(activity: AppointmentActivity)
    fun inject(fragment: AppointmentPersonInfoFragment)
    fun inject(fragment: AppointmentDemandFragment)
    fun inject(fragment: AppointmentResultFragment)
}
