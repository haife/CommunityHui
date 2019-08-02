package com.kaiwukj.android.communityhui.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment;
import com.kaiwukj.android.communityhui.app.constant.SPParam;
import com.kaiwukj.android.communityhui.di.component.DaggerChatComponent;
import com.kaiwukj.android.communityhui.greendao.gen.MessageEntityDao;
import com.kaiwukj.android.communityhui.jpush.DaoHelper;
import com.kaiwukj.android.communityhui.jpush.MessageEntity;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.presenter.ChatPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.ChatListAdapter;
import com.kaiwukj.android.communityhui.utils.SPUtils;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc 消息列表
 */
public class PushMessageListFragment extends BaseSupportFragment<ChatPresenter> implements ChatContract.View {
    @BindView(R.id.rv_push_list)
    RecyclerView mPushListRv;
    @BindView(R.id.smart_refresh_view_push)
    SmartRefreshLayout mSmartRefreshLayout;
    private boolean isConflict;
    private ChatListAdapter mChatAdapter;
    protected boolean hidden;


    public static PushMessageListFragment newInstance() {
        Bundle args = new Bundle();
        PushMessageListFragment fragment = new PushMessageListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChatComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_message_push, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<MessageEntity> messageEntityList = DaoHelper.getMessageDao(getContext()).queryBuilder()
                .where(MessageEntityDao.Properties.Title.eq(SPUtils.getInstance().getString(SPParam.SP_ALIAS))).list();
        Collections.reverse(messageEntityList);


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
