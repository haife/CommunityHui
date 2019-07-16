package com.kaiwukj.android.communityhui.mvp.ui.widget.login;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 * 登录倒计时
 */
public class LoginTimeCount extends CountDownTimer {

    private Button mTimerButton;
    public static long MILL_IS_IN_FUTURE = 60000;
    public static long COUNT_DOWN_INTERVAL = 1000;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    public LoginTimeCount(long millisInFuture, long countDownInterval, Button mTimerButton) {
        super(millisInFuture, countDownInterval);
        this.mTimerButton = mTimerButton;
    }


    @Override
    public void onTick(long millisUntilFinished) {
        if (mTimerButton != null) {
            mTimerButton.setClickable(false);
            mTimerButton.setText("" + millisUntilFinished / 1000 + " 秒后可重新发送");
        }

    }

    @Override
    public void onFinish() {
        if (mTimerButton != null) {
            mTimerButton.setText("重新获取验证码");
            mTimerButton.setClickable(true);
        }
    }
}
