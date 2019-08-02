package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseQITokenResult
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.listener.OnSubDataUpdateListener
import com.kaiwukj.android.communityhui.utils.QiNiuUtil
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.integration.AppManager
import com.kaiwukj.android.mcas.mvp.BasePresenter
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class EditMineInfoPresenter
@Inject
constructor(model: EditMineInfoContract.Model, rootView: EditMineInfoContract.View) :
        BasePresenter<EditMineInfoContract.Model, EditMineInfoContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    /**
     * 我的收藏
     * @param userId String
     */
    fun requestMyCollection(request: MineCollectionRequest) {
        mModel.requestMyCollection(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle<MineCollectionResult>(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<MineCollectionResult>(mErrorHandler) {
                    override fun onNext(result: MineCollectionResult) {
                        if (result.code == Api.RequestSuccess) {
                            mRootView.onGetMyCollectionData(result.result.list)
                        }
                    }
                })
    }

    /**
     * 更新个人信息
     * @param userId String
     */
    fun updateMineInfoData(userInfo: MineUserInfoResult) {
        mModel.updateMineInfoData(userInfo)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle<BaseStatusResult>(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(result: BaseStatusResult) {
                        if (result.code == Api.RequestSuccess) {
                            mRootView.showLoading()
                        } else {
                            mRootView.hideLoading()
                        }
                    }
                })
    }

    /**
     * 获取地址
     */
    fun requestMyAddress() {
        mModel.requestMyAddress()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<MyAddressResult>(mErrorHandler) {
                    override fun onNext(data: MyAddressResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.onGetMyAddressList(data)
                        } else {

                        }
                    }
                })
    }

    /**
     * 编辑地址
     */
    fun upDateMyAddress(request: MyAddressResult) {
        mModel.upDateMyAddress(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showLoading()
                        } else {

                        }
                    }
                })
    }


    /**
     * 新增地址
     */
    fun addMyAddress(request: MyAddressResult) {
        mModel.addMyAddress(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showLoading()
                        }
                    }
                })
    }


    /**
     * 删除
     */
    fun deleteMyAddress(addressId:Int) {
        mModel.deleteMyAddress(addressId)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showLoading()
                        }
                    }
                })
    }


    /**
     * 上传头像
     */
    fun requestQIToken(paths: String) {
        mModel.requestQIToken()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<BaseQITokenResult>(mErrorHandler) {
                    override fun onNext(data: BaseQITokenResult) {
                        if (data.code == Api.RequestSuccess) {
                            var qiNiuUtil = QiNiuUtil(OnSubDataUpdateListener { urls ->
                                for (bean in urls.orEmpty()) {
                                    mRootView.showMessage(bean.imgUrl)
                                }
                            })
                            //上传图片
                            qiNiuUtil.uploadImageToQiniu(paths, data.result)
                        }
                    }
                })
    }




    override fun onDestroy() {
        super.onDestroy();
    }
}
