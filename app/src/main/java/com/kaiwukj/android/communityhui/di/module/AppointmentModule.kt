package com.kaiwukj.android.communityhui.di.module

import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.model.AppointmentModel
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
class AppointmentModule(private val view: AppointmentContract.View) {
    @ActivityScope
    @Provides
    fun provideAppointmentView(): AppointmentContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAppointmentModel(model: AppointmentModel): AppointmentContract.Model {
        return model
    }
}
