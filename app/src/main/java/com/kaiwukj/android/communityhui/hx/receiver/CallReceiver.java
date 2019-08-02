/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaiwukj.android.communityhui.hx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.kaiwukj.android.communityhui.hx.DemoHelper;
import com.kaiwukj.android.communityhui.hx.UserCacheManager;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!DemoHelper.getInstance().isLoggedIn())
            return;
        //username
        String from = intent.getStringExtra("from");
        //call type
        String type = intent.getStringExtra("type");
        // 缓存用户昵称头像
        String ext = EMClient.getInstance().callManager().getCurrentCallSession().getExt();
        UserCacheManager.save(ext);
        EMLog.d("CallReceiver", "app received a incoming call");
    }

}
