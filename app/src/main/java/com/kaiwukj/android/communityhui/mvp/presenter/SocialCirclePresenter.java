package com.kaiwukj.android.communityhui.mvp.presenter;

import android.app.Application;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseQITokenResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.SubImageBean;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CommentOtherRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.PostCardRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardCommentResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCardCommentAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleTopicAdapter;
import com.kaiwukj.android.communityhui.utils.QiNiuUtil;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader;
import com.kaiwukj.android.mcas.integration.AppManager;
import com.kaiwukj.android.mcas.mvp.BasePresenter;
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


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
    private List<CircleCardCommentResult> mCommentListList;
    @Inject
    private SocialCardCommentAdapter mCommentAdapter;

    @Inject
    public SocialCirclePresenter(SocialCircleContract.Model model, SocialCircleContract.View rootView) {
        super(model, rootView);
    }


    /**
     * 圈子列表
     */
    public void getHomeRecommendData(CircleHomeRequest request, boolean pullToRefresh) {
        mModel.requestCircleHomeList(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .doFinally(() -> {
                    if (pullToRefresh)
                        mRootView.finishRefresh();
                    else
                        mRootView.finishLoadMore(false);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<CircleHomeResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleHomeResult result) {
                        if (result.getCode().equals(Api.RequestSuccess)) {
                            if (pullToRefresh) {
                                mDataList.clear();
                                mRootView.finishRefresh();
                                mDataList.addAll(result.getResult().getList());
                            } else {
                                if (request.getPages() > 1 && result.getResult().getList().size() > 0)
                                    mRootView.finishLoadMore(true);
                                else
                                    mDataList.addAll(result.getResult().getList());
                                mRootView.finishLoadMore(false);
                            }
                            mCircleListAdapter.notifyDataSetChanged();

                        }
                    }


                });
    }


    /**
     * 帖子主题
     */
    public void requestCircleCardList() {
        mModel.requestCircleCardList()
                .subscribeOn(Schedulers.io())
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
        mModel.requestCircleHotList()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<CircleHotResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleHotResult result) {
                        mHotList.addAll(result.getResult().getList());
                        mRootView.showLoading();
                    }
                });
    }

    /**
     * 发帖
     */
    public void postSocialCard(PostCardRequest request) {
        mModel.postSocialCard(request)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    @Override
                    public void onNext(BaseStatusResult result) {
                        if (result.getCode().equals(Api.RequestSuccess))
                            mRootView.showMessage(mRootView.getCtx().getString(R.string.social_post_card_success));
                    }
                });
    }

    /**
     * 获取帖子详情
     *
     * @param id
     */
    public void requestSocialCardDetail(int id) {
        mModel.requestSocialCardDetail(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<CircleCardDetailResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleCardDetailResult result) {
                        if (result.getCode().equals(Api.RequestSuccess)) {
                            mRootView.onGetCardDetailResult(result.getResult());
                        }
                    }
                });
    }

    /**
     * 获取帖子的评论列表
     *
     * @param id 帖子ID
     */
    public void requestCommentList(int id, int page) {
        mModel.requestCommentList(id, page)
                .subscribeOn(Schedulers.io())
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<CircleCardCommentResult>(mErrorHandler) {
                    @Override
                    public void onNext(CircleCardCommentResult result) {
                        if (result.getCode().equals(Api.RequestSuccess)) {
                            if (page > 1 && result.getResult().getList().size() == 0) {
                                //加载完全部数据
                                mRootView.finishLoadMore(true);
                            }
                            mRootView.finishLoadMore(false);
                            mCommentListList.addAll(result.getResult().getList());
                            mCommentAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    /**
     * 发表或者回复别人的帖子
     */
    public void requestCommentOther(CommentOtherRequest request) {
        mModel.requestCommentOther(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    @Override
                    public void onNext(BaseStatusResult result) {
                        if (result.getCode().equals(Api.RequestSuccess)) {
                            mRootView.showMessage("评论成功");
                        }
                    }
                });
    }


    /**
     * 获取个人主页
     */
    public void requestSocialHomePage(String userId) {
        SocialUserHomePageRequest request = new SocialUserHomePageRequest();
        request.setId(userId);
        mModel.requestSocialHomePage(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<SocialUserHomePageResult>(mErrorHandler) {
                    @Override
                    public void onNext(SocialUserHomePageResult result) {
                        if (result.getCode().equals(Api.RequestSuccess)) {
                            mRootView.onGetOtherHomePageData(result.getResult());
                        }
                    }
                });
    }


    /**
     * 获取七牛云Token
     */
    public void requestQIToken(PostCardRequest request, List<String> paths) {
        mModel.requestQIToken()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<BaseQITokenResult>(mErrorHandler) {
                    @Override
                    public void onNext(BaseQITokenResult result) {
                        if (result.getCode().equals(Api.RequestSuccess)) {
                            QiNiuUtil qiNiuUtil = new QiNiuUtil(urls -> {
                                List<String> imageUlrs = new ArrayList<>();
                                for (SubImageBean bean : urls) {
                                    imageUlrs.add(bean.getImgUrl());
                                }
                                request.setImgList(imageUlrs);
                                postSocialCard(request);
                            });
                            //上传图片
                            qiNiuUtil.uploadImagesToQiniu(paths, result.getResult());
                        }
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
