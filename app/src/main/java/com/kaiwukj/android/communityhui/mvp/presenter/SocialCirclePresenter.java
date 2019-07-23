package com.kaiwukj.android.communityhui.mvp.presenter;

import android.app.Application;

import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleTopicAdapter;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader;
import com.kaiwukj.android.mcas.integration.AppManager;
import com.kaiwukj.android.mcas.mvp.BasePresenter;
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
@ActivityScope
public class SocialCirclePresenter extends BasePresenter<SocialCircleContract.Model, SocialCircleContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<CircleCardResult> mCardResults;
    @Inject
    List<CircleHotResult> mHotList;
    @Inject
    List<CircleHomeResult> mDataList;
    @Inject
    SocialCircleListAdapter mCircleListAdapter;

    @Inject
    SocialCircleTopicAdapter mCircleTopicAdapter;

    @Inject
    public SocialCirclePresenter(SocialCircleContract.Model model, SocialCircleContract.View rootView) {
        super(model, rootView);
    }


    /**
     * 圈子列表
     */
    public void getHomeRecommendData(CircleHomeRequest request, boolean pullToRefresh) {
        boolean isEvictCache = pullToRefresh;
        mModel.requestCircleHomeList(request, isEvictCache)
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new ErrorHandleSubscriber<CircleHomeResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleHomeResult result) {
                        if (result.getCode() == Api.RequestSuccess) {
                            mDataList.addAll(result.getResult().getList());
                            mCircleListAdapter.notifyDataSetChanged();
                        }

//                        if (pullToRefresh) {
//                            mDataList.clear();
//                            mRootView.finishRefresh();
//                        } else {
//                            if (page > 1 && result.getResult().getList().size() > 0) {
//                                mRootView.finishLoadMore(true);
//                            }
//                            mRootView.finishLoadMore(false);
//                        }


                    }
                });
    }


    /**
     * 帖子主题
     */
    public void requestCircleCardList() {
        mModel.requestCircleCardList()
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<CircleCardResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleCardResult result) {
                        mCardResults.addAll(result.getResult());
                        mCircleTopicAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 热门
     */
    public void requestCircleHotList() {
        mModel.requestCircleHotList().compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))
                .subscribe(new ErrorHandleSubscriber<CircleHotResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleHotResult result) {
                        mHotList.addAll(result.getResult().getList());
                        mRootView.showMessage("");
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
