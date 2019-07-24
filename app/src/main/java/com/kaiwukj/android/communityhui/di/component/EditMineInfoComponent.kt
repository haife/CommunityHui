package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.EditMineInfoActivity
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
@Component(modules = [EditMineInfoModule::class], dependencies = [AppComponent::class])
interface EditMineInfoComponent {
    fun inject(activity: EditMineInfoActivity)
    fun inject(fragment: MineAddressListFragment)
    fun inject(fragment: EditMineAddressFragment)
    fun inject(fragment: PersonHomePageFragment)
    fun inject(fragment: MineCollectionFragment)
    fun inject(fragment: CollectionStaffListFragment)
    fun inject(fragment: CollectionStoreListFragment)
}
