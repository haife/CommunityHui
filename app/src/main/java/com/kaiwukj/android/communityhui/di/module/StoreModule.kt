package com.kaiwukj.android.communityhui.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.model.StoreModel
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
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
class StoreModule(private val view: StoreContract.View) {
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


    @ActivityScope
    @Provides
    fun provideLayoutManager(): RecyclerView.LayoutManager {
        return object : LinearLayoutManager(view.getContextView()) {
            override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
                return 500
            }
        }
    }

    @ActivityScope
    @Provides
    fun provideStoreList(): ArrayList<StoreListResult> {
        return ArrayList()
    }

    @ActivityScope
    @Provides
    fun provideStoreListAdapter(list: ArrayList<StoreListResult>): StoreListAdapter {
        return StoreListAdapter(list, R.layout.recycle_item_store_list, view.getContextView()!!)
    }

}
