package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseImageView;
import com.hyphenate.util.DateUtils;
import com.kaiwukj.android.communityhui.R;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;


public class ChatListAdapter extends BaseQuickAdapter<EMConversation, BaseViewHolder> {
    private Context mContext;

    public ChatListAdapter(Context context, int layoutResId, @Nullable List<EMConversation> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, EMConversation item) {
        String username = item.conversationId();
        TextView name = helper.getView(R.id.name);
        EaseImageView avatar = helper.getView(R.id.avatar);
        TextView unreadLabel = helper.getView(R.id.unread_msg_number);
        TextView message = helper.getView(R.id.message);
        TextView time = helper.getView(R.id.time);
        ImageView msg_state = helper.getView(R.id.msg_state);
        EaseUserUtils.setUserAvatar(mContext, username, avatar);
        EaseUserUtils.setUserNick(username, name);
        helper.getView(R.id.mentioned).setVisibility(View.GONE);
        EaseAvatarOptions avatarOptions = EaseUI.getInstance().getAvatarOptions();
        if (avatarOptions != null && avatar instanceof EaseImageView) {
            EaseImageView avatarView = ((EaseImageView) avatar);
            if (avatarOptions.getAvatarShape() != 0)
                avatarView.setShapeType(avatarOptions.getAvatarShape());
            if (avatarOptions.getAvatarBorderWidth() != 0)
                avatarView.setBorderWidth(avatarOptions.getAvatarBorderWidth());
            if (avatarOptions.getAvatarBorderColor() != 0)
                avatarView.setBorderColor(avatarOptions.getAvatarBorderColor());
            if (avatarOptions.getAvatarRadius() != 0)
                avatarView.setRadius(avatarOptions.getAvatarRadius());
        }
        if (item.getUnreadMsgCount() > 0) {
            // show unread message count
            unreadLabel.setText(String.valueOf(item.getUnreadMsgCount()));
            unreadLabel.setVisibility(View.VISIBLE);
        } else {
            unreadLabel.setVisibility(View.INVISIBLE);
        }
        if (item.getAllMsgCount() != 0) {
            // show the content of latest message
            EMMessage lastMessage = item.getLastMessage();
            String content = null;
//            if(cvsListHelper != null){
//                content = cvsListHelper.onSetItemSecondaryText(lastMessage);
//            }
            message.setText(EaseSmileUtils.getSmiledText(mContext, EaseCommonUtils.getMessageDigest(lastMessage, (mContext))),
                    TextView.BufferType.SPANNABLE);
            if (content != null) {
                message.setText(content);
            }
            time.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
            if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                msg_state.setVisibility(View.VISIBLE);
            } else {
                msg_state.setVisibility(View.GONE);
            }
        }


    }
}
