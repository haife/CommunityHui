package com.kaiwukj.android.communityhui.mvp.ui.activity;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.exceptions.HyphenateException;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity;
import com.kaiwukj.android.communityhui.app.constant.Constant;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.presenter.ChatPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CommonFragmentPagerAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.widget.im.StateButton;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-06-27
 * 聊天界面
 */
public class ChatRecordActivity extends BaseSwipeBackActivity<ChatPresenter> implements ChatContract.View {
    @BindView(R.id.emotion_voice)
    ImageView emotionVoice;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.voice_text)
    TextView voiceText;
    @BindView(R.id.emotion_button)
    ImageView emotionButton;
    @BindView(R.id.emotion_add)
    ImageView emotionAdd;
    @BindView(R.id.emotion_send)
    StateButton emotionSend;
    @BindView(R.id.emotion_layout)
    RelativeLayout emotionLayout;
    @BindView(R.id.qtb_chat_message)
    QMUITopBar qtb_chat_message;
    @BindView(R.id.message_list)
    EaseChatMessageList messageList;

    protected boolean haveMoreData = true;
    protected int pagesize = 20;
    private ArrayList<Fragment> fragments;
    private CommonFragmentPagerAdapter adapter;
    protected int chatType;
    private LinearLayoutManager layoutManager;
    private List<EMMessage> messageInfos;
    protected boolean isRoaming = false;
    protected EMConversation conversation;
    private ExecutorService fetchQueue;
    //录音相关
    protected ListView listView;
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    String toChatUsername;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {

        return R.layout.activity_chat_record;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        toChatUsername = getIntent().getExtras().getString(Constant.EXTRA_USER_ID);
        chatType = getIntent().getExtras().getInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        qtb_chat_message.setTitle(toChatUsername);
        if (chatType == EaseConstant.CHATTYPE_SINGLE) {
            // set title
            if (EaseUserUtils.getUserInfo(toChatUsername) != null) {
                EaseUser user = EaseUserUtils.getUserInfo(toChatUsername);
                if (user != null) {
                    qtb_chat_message.setTitle(user.getNickname());
                }
            }
        }

        if (isRoaming) {
            fetchQueue = Executors.newSingleThreadExecutor();
        }

        initWidget();
    }

    private void initWidget() {
        listView = messageList.getListView();
        fragments = new ArrayList<>();
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);

        onConversationInit();
    }


    protected void onConversationInit() {
        conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
        conversation.markAllMessagesAsRead();
        // the number of messages loaded into conversation is getChatOptions().getNumberOfMessagesLoaded
        // you can change this number

        if (!isRoaming) {
            final List<EMMessage> msgs = conversation.getAllMessages();
            int msgCount = msgs != null ? msgs.size() : 0;
            if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
                String msgId = null;
                if (msgs != null && msgs.size() > 0) {
                    msgId = msgs.get(0).getMsgId();
                }
                conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
            }
        } else {
            fetchQueue.execute(() -> {
                try {
                    EMClient.getInstance().chatManager().fetchHistoryMessages(
                            toChatUsername, EaseCommonUtils.getConversationType(chatType), pagesize, "");
                    final List<EMMessage> msgs = conversation.getAllMessages();
                    int msgCount = msgs != null ? msgs.size() : 0;
                    if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
                        String msgId = null;
                        if (msgs != null && msgs.size() > 0) {
                            msgId = msgs.get(0).getMsgId();
                        }
                        conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
                    }
                    messageList.refreshSelectLast();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(ChatRecordActivity.class);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

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
}
