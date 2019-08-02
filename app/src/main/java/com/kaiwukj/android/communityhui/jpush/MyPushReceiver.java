package com.kaiwukj.android.communityhui.jpush;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/8/2
 * @desc $desc
 */
public class MyPushReceiver extends JPushMessageReceiver {
    public MyPushReceiver() {
        super();
    }

    @Override
    public Notification getNotification(Context context, NotificationMessage message) {
        return super.getNotification(context, message);
    }
    //自定义消息
    @Override
    public void onMessage(Context context, CustomMessage message) {
        super.onMessage(context, message);
        //DaoHelper.getMessageDao(context).insert(new MessageEntity(System.currentTimeMillis(), SPUtils.getInstance().getString(SPParam.SP_ALIAS),message.message));
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        super.onNotifyMessageOpened(context, message);
    }
    //通知消息
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        super.onNotifyMessageArrived(context, message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        super.onNotifyMessageDismiss(context, message);
    }

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
    }

    @Override
    public void onConnected(Context context, boolean b) {
        super.onConnected(context, b);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage message) {
        super.onCommandResult(context, message);
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        super.onMultiActionClicked(context, intent);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage message) {
        super.onTagOperatorResult(context, message);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage message) {
        super.onCheckTagOperatorResult(context, message);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage message) {
        super.onAliasOperatorResult(context, message);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage message) {
        super.onMobileNumberOperatorResult(context, message);
    }
}
