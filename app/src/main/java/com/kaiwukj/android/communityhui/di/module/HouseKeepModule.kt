package com.kaiwukj.android.communityhui.di.module


import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.model.HouseKeepModel
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Module
import dagger.Provides


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政
 */
@Module
//构建HouseKeepModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class HouseKeepModule(private val view: HouseKeepContract.View) {
    @ActivityScope
    @Provides
    fun provideHouseKeepView(): HouseKeepContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideHouseKeepModel(model: HouseKeepModel): HouseKeepContract.Model {
        return model
    }
}
