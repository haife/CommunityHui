package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.HouseKeepActivity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HouseKeepFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HouseKeepListFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Component

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政
 */
@ActivityScope
@Component(modules = [HouseKeepModule::class], dependencies = [AppComponent::class])
interface HouseKeepComponent {
    fun inject(activity: HouseKeepActivity)
    fun inject(fragment: HouseKeepFragment)
    fun inject(fragment: HouseKeepListFragment)
}
