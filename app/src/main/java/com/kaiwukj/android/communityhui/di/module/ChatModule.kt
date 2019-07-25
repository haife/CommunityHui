package com.kaiwukj.android.communityhui.di.module

import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.model.StoreModel
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
@Module
class ChatModule(private val view: StoreContract.View) {
    @ActivityScope
    @Provides
    fun provideStoreView(): StoreContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideStoreModel(model: StoreModel): StoreContract.Model {
        return model
    }


}
