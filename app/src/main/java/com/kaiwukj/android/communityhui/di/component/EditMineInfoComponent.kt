package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.ui.activity.EditMineInfoActivity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.EditMineAddressFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.MineAddressListFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.MineCollectionFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.PersonHomePageFragment
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
    fun inject(activity: MineAddressListFragment)
    fun inject(activity: EditMineAddressFragment)
    fun inject(activity: PersonHomePageFragment)
    fun inject(activity: MineCollectionFragment)
}
