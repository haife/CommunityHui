package com.kaiwukj.android.communityhui.di.module


import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.model.EditMineInfoModel
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class EditMineInfoModule(private val view: EditMineInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideEditMineInfoView(): EditMineInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditMineInfoModel(model: EditMineInfoModel): EditMineInfoContract.Model {
        return model
    }
}
