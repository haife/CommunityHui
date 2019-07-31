package com.kaiwukj.android.communityhui.hx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

import java.util.List;

/**
 * you can new an EaseChatFragment to use or you can inherit it to expand.
 * You need call setArguments to pass chatType and userId
 * <br/>
 * <br/>
 * you can see ChatActivity in demo for your reference
 */
public class HxEaseChatFragment extends EaseChatFragment implements EMMessageListener, EaseChatFragment.EaseChatFragmentHelper {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setChatFragmentHelper(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        for (EMMessage message : messages) {
            String username = null;
            UserCacheManager.save(message.ext());
            // group message
            if (message.getChatType() == EMMessage.ChatType.GroupChat || message.getChatType() == EMMessage.ChatType.ChatRoom) {
                username = message.getTo();
            } else {
                // single chat message
                username = message.getFrom();
            }
            // if the message is for current conversation
            if (username.equals(toChatUsername) || message.getTo().equals(toChatUsername) || message.conversationId().equals(toChatUsername)) {
                messageList.refreshSelectLast();
                conversation.markMessageAsRead(message.getMsgId());
            }

            EaseUI.getInstance().getNotifier().vibrateAndPlayTone(message);
        }

    }


    @Override
    public void onSetMessageAttributes(EMMessage message) {
        UserCacheManager.setMsgExt(message);
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
