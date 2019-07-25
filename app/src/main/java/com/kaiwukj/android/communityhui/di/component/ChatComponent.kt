package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.ChatModule
import com.kaiwukj.android.communityhui.mvp.ui.fragment.ChatFragment
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
@Component(modules = [ChatModule::class], dependencies = [AppComponent::class])
interface ChatComponent {
    fun inject(activity: ChatFragment)

}
