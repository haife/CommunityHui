package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.mvp.contract.MainContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.request.VersionUpdateRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.VersionUpdateResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.integration.AppManager
import com.kaiwukj.android.mcas.mvp.BasePresenter
import com.kaiwukj.android.mcas.utils.McaUtils.startActivity
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc Main Screen
 */
@ActivityScope
class MainPresenter
@Inject
constructor(model: MainContract.Model, rootView: MainContract.View) :
        BasePresenter<MainContract.Model, MainContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    /**
     *版本更新检查
     */
    fun requestVersionUpdate() {
        val versionName = getAppVersionName(mRootView.getActivity())
        mModel.requestVersionUpdate(VersionUpdateRequest(versionName))
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<VersionUpdateResult>(mErrorHandler) {
                    override fun onNext(data: VersionUpdateResult) {
                        if (data.code == Api.RequestSuccess) {
                            if (versionName != data.result.versionNo) {
                                val dialog = MaterialDialog(mRootView.getActivity())
                                        .customView(R.layout.custom_updata_version_update, noVerticalPadding = true)
                                dialog.cornerRadius(8f)
                                dialog.show()

                                val customView = dialog.getCustomView()
                                customView.findViewById<TextView>(R.id.tv_new_version_name).text = "V ${data.result.versionNo}"
                                customView.findViewById<TextView>(R.id.tv_version_update_content).text = data.result.content.replace(" ", "\n")
                                customView.findViewById<TextView>(R.id.tv_refuse_update).setOnClickListener { dialog.dismiss() }
                                if (data.result.forceFlag == 1) {
                                    dialog.cancelable(false)
                                    dialog.cancelOnTouchOutside(false)
                                    customView.findViewById<TextView>(R.id.tv_refuse_update).visibility = View.GONE
                                }

                                customView.findViewById<TextView>(R.id.tv_update_now).setOnClickListener {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data = Uri.parse(data.result.channelInfo)
                                    startActivity(intent)
                                }
                            }
                            mRootView.onVersionUpdateResult(data)
                        }
                    }


                })
    }


    fun getAppVersionName(context: Context): String {
        var appVersionName = ""
        try {
            val packageInfo = context.applicationContext
                    .packageManager
                    .getPackageInfo(context.packageName, 0)
            appVersionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
        }

        return appVersionName
    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
