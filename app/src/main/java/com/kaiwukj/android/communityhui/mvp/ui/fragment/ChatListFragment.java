package com.kaiwukj.android.communityhui.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment;
import com.kaiwukj.android.communityhui.app.constant.Constant;
import com.kaiwukj.android.communityhui.di.component.DaggerChatComponent;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.presenter.ChatPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.activity.ChatActivity;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.ChatListAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
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
public class ChatListFragment extends BaseSupportFragment<ChatPresenter> implements ChatContract.View {
    @BindView(R.id.rv_chat_contract)
    RecyclerView mChatListRv;
    @BindView(R.id.smart_refresh_view_chat)
    SmartRefreshLayout smart_refresh_view_chat;

    private final static int MSG_REFRESH = 2;
    protected List<EMConversation> conversationList = new ArrayList<>();
    private boolean isConflict;
    private ChatListAdapter mChatAdapter;
    protected boolean hidden;
    protected Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    onConnectionDisconnected();
                    break;
                case 1:
                    onConnectionConnected();
                    break;

                case MSG_REFRESH: {
                    conversationList.clear();
                    conversationList.addAll(loadConversationList());
                    mChatAdapter.notifyDataSetChanged();
                    break;
                }
                default:
                    break;
            }
        }
    };

    public static ChatListFragment newInstance() {
        Bundle args = new Bundle();
        ChatListFragment fragment = new ChatListFragment();
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
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //获取联系人个数
        conversationList.addAll(loadConversationList());
        mChatListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mChatListRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mChatAdapter = new ChatListAdapter(getContext(), R.layout.ease_row_chat_history, conversationList);
        mChatListRv.setAdapter(mChatAdapter);
        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty_view_common_container, null);
        ImageView iv = emptyView.findViewById(R.id.iv_empty_view_type);
        iv.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.icon_empty_view_message));
        mChatAdapter.setEmptyView(emptyView);
        EMClient.getInstance().addConnectionListener(connectionListener);
        //刷新回调
        smart_refresh_view_chat.setOnRefreshListener(refreshLayout -> {
            refresh();
            smart_refresh_view_chat.finishRefresh();
        });

        mChatAdapter.setOnItemClickListener((adapter, view1, position) -> {
            EMConversation conversation = conversationList.get(position);
            String username = conversation.conversationId();
            if (username.equals(EMClient.getInstance().getCurrentUser()))
                Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
            else {
                // start chat acitivity
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                if (conversation.isGroup()) {
                    if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
                        // it's group chat
                        intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_CHATROOM);
                    } else {
                        intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_GROUP);
                    }
                }
                // it's single chat
                intent.putExtra(Constant.EXTRA_USER_ID, username);
                intent.putExtra(Constant.EXTRA_USER_NAME, username);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
    }


    /**
     * refresh ui
     */
    public void refresh() {
        if (!handler.hasMessages(MSG_REFRESH)) {
            handler.sendEmptyMessage(MSG_REFRESH);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * load conversation list
     *
     * @return +
     */
    protected List<EMConversation> loadConversationList() {
        // get all conversations
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        /**
         * lastMsgTime will change if there is new message during sorting
         * so use synchronized to make sure timestamp of last message won't change.
         */
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }


    /**
     * sort conversations according time stamp of last message
     *
     * @param conversationList
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, (con1, con2) -> {

            if (con1.first.equals(con2.first)) {
                return 0;
            } else if (con2.first.longValue() > con1.first.longValue()) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    protected EMConnectionListener connectionListener = new EMConnectionListener() {

        @Override
        public void onDisconnected(int error) {
            if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE || error == EMError.SERVER_SERVICE_RESTRICTED
                    || error == EMError.USER_KICKED_BY_CHANGE_PASSWORD || error == EMError.USER_KICKED_BY_OTHER_DEVICE) {
                isConflict = true;
            } else {
                handler.sendEmptyMessage(0);
            }
        }

        @Override
        public void onConnected() {
            handler.sendEmptyMessage(1);
        }
    };

    @Override
    public void onSupportVisible() {
        refresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().removeConnectionListener(connectionListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isConflict) {
            outState.putBoolean("isConflict", true);
        }
    }

    /**
     * connected to server
     */
    protected void onConnectionConnected() {
        //errorItemContainer.setVisibility(View.GONE);
    }

    /**
     * disconnected with server
     */
    protected void onConnectionDisconnected() {
        //errorItemContainer.setVisibility(View.VISIBLE);
    }


    @Override
    public void post(Runnable runnable) {

    }

}
