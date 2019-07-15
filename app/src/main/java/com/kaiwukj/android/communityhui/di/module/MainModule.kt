package com.kaiwukj.android.communityhui.di.module


import dagger.Module
import dagger.Provides

import com.kaiwukj.android.communityhui.mvp.contract.MainContract
import com.kaiwukj.android.communityhui.mvp.model.MainModel
import com.kaiwukj.android.mcas.di.scope.ActivityScope


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
@Module
class MainModule(private val view: MainContract.View) {
    @ActivityScope
    @Provides
    fun provideMainView(): MainContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMainModel(model: MainModel): MainContract.Model {
        return model
    }
}
