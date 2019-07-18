package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.StoreModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.StoreActivity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreDetailFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreListFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreSortListFragment
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
@Component(modules = [StoreModule::class], dependencies = [AppComponent::class])
interface StoreComponent {
    fun inject(activity: StoreActivity)
    fun inject(fragment: StoreListFragment)
    fun inject(fragment: StoreSortListFragment)
    fun inject(fragment: StoreDetailFragment)
}
