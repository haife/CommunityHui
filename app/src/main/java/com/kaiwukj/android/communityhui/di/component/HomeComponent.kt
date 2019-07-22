package com.kaiwukj.android.communityhui.di.component

import com.kaiwukj.android.communityhui.di.module.HomeModule
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.di.scope.FragmentScope
import dagger.Component


@FragmentScope
@Component(modules = [HomeModule::class], dependencies = [AppComponent::class])
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}
