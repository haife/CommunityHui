package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.MineActivity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.*
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Component


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc
 */
@ActivityScope
@Component(modules = [MineModule::class], dependencies = [AppComponent::class])
interface MineComponent {
    fun inject(activity: MineActivity)
    fun inject(fragment: MineFragment)
    fun inject(fragment: ServiceOrderFragment)
    fun inject(fragment: ServiceOrderDetailFragment)
    fun inject(fragment: EvaluateServiceFragment)
    fun inject(fragment: ServiceOrderListFragment)
    fun inject(fragment: SettingFragment)

}
