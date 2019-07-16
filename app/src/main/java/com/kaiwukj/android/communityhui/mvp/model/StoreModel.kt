package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson

import javax.inject.Inject

import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel


@ActivityScope
class StoreModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), StoreContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
