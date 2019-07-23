package com.kaiwukj.android.communityhui.di.module

import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.model.MineModel
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Module
import dagger.Provides


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  
 */
@Module
class MineModule(private val view: MineContract.View) {
    @ActivityScope
    @Provides
    fun provideMineView(): MineContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMineModel(model: MineModel): MineContract.Model {
        return model
    }

}
