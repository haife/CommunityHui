package com.kaiwukj.android.communityhui.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * @author Eddie Android Developer
 * @company Q | 樽尚汇
 * @since 2019/1/11
 * TODO: 输入法工具类
 */
public class InputMethodUtils {


    /**
     * Show Soft Input
     *
     * @param view
     * @return
     */
    public static boolean showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * Show Soft Input
     *
     * @param activity
     * @return
     */
    public static boolean showSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            return imm.showSoftInput(activity.getCurrentFocus(), InputMethodManager.SHOW_FORCED);
        }
        return false;
    }

    /**
     * Hide Soft Input
     *
     * @param view
     * @return
     */
    public static boolean hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Hide Soft Input
     *
     * @param activity
     * @return
     */
    public static boolean hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            return imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        return false;
    }

    /**
     * Judge whether input method is active
     *
     * @param context
     * @return
     */
    public static boolean isActive(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }
}
