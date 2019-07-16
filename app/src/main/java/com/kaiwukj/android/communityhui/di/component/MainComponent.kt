package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.MainModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.MainActivity
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Component


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
@ActivityScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}
