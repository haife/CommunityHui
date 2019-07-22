package com.kaiwukj.android.communityhui.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.model.HomeModel
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HRecommendAdapter
import com.kaiwukj.android.mcas.di.scope.FragmentScope
import dagger.Module
import dagger.Provides


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
@Module
class HomeModule(private val view: HomeContract.View) {
    @FragmentScope
    @Provides
    fun provideHomeView(): HomeContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideHomeModel(model: HomeModel): HomeContract.Model {
        return model
    }


    @FragmentScope
    @Provides
    fun provideLayoutManager(): RecyclerView.LayoutManager {
        return object : LinearLayoutManager(view.getFragment().context) {
            override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
                return 500
            }
        }
    }

    @FragmentScope
    @Provides
    fun provideRecommendDataList(): MutableList<HRecommendMultiItemEntity> {
        return ArrayList()
    }

    @FragmentScope
    @Provides
    fun provideHRecommendAdapter(list: MutableList<HRecommendMultiItemEntity>): HRecommendAdapter {
        return HRecommendAdapter(list, view.getFragment().context!!)
    }



}
