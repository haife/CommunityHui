package com.kaiwukj.android.communityhui.mvp.ui.widget.home;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.RequiresApi;


public class CustomPopupWindow {
    private PopupWindow mPopupWindow;
    private View contentview;
    private static Activity mContext;


    public CustomPopupWindow(Builder builder) {
        contentview = LayoutInflater.from(mContext).inflate(builder.contentviewid, null);
        mPopupWindow = new PopupWindow(contentview, builder.width, builder.height, builder.fouse);
        //需要跟 setBackGroundDrawable 结合
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(builder.outsidecancel);
//        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(builder.animstyle);
        mPopupWindow.setOnDismissListener(() -> WindowUtil.setBackgroundAlpha(mContext, 1f));
        contentview.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mPopupWindow.dismiss();
                mPopupWindow = null;
                return true;
            }
            return false;

        });
    }

    public boolean isShowing(){
        return mPopupWindow.isShowing();
    }


    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    /**
     * popup 消失
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 根据id获取view
     *
     * @param viewid
     * @return
     */
    public View getItemView(int viewid) {
        if (mPopupWindow != null) {
            return this.contentview.findViewById(viewid);
        }
        return null;
    }

    /**
     * 根据父布局，显示位置
     *
     * @param rootviewid
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    public CustomPopupWindow showAtLocation(
            int rootviewid, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            View rootview = LayoutInflater
                    .from(mContext).inflate(rootviewid, null);
            mPopupWindow.showAtLocation(rootview, gravity, x, y);
            WindowUtil.setBackgroundAlpha(mContext, 0.5f);
        }
        return this;
    }

    /**
     * 根据id获取view ，并显示在该view的位置
     *
     * @param targetviewId
     * @param gravity
     * @param offx
     * @param offy
     * @return
     */
    public CustomPopupWindow showAsLaction(
            int targetviewId,
            int gravity,
            int offx, int offy) {
        if (mPopupWindow != null) {
            View targetview = LayoutInflater
                    .from(mContext)
                    .inflate(targetviewId, null);
            mPopupWindow.showAtLocation(targetview,
                    gravity, offx, offy);
            WindowUtil.setBackgroundAlpha(mContext, 0.5f);
        }
        return this;
    }

    public CustomPopupWindow showBottom(
            int targetviewId) {
        if (mPopupWindow != null) {
            View targetview = LayoutInflater
                    .from(mContext)
                    .inflate(targetviewId, null);
            mPopupWindow.showAtLocation(targetview,
                    Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
            WindowUtil.setBackgroundAlpha(mContext, 0.5f);
        }
        return this;
    }

    public CustomPopupWindow showCenter(
            int targetviewId
    ) {
        if (mPopupWindow != null) {
            View targetview = LayoutInflater
                    .from(mContext)
                    .inflate(targetviewId, null);
            mPopupWindow.showAtLocation(targetview,
                    Gravity.CENTER, 0, 0);
            WindowUtil.setBackgroundAlpha(mContext, 0.5f);
        }
        return this;
    }

    /**
     * 显示在 targetview 的不同位置
     *
     * @param targetview
     * @param gravity
     * @param offx
     * @param offy
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CustomPopupWindow showAsDropDown(View targetview, int gravity, int offx, int offy) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(targetview, gravity, offx, offy);
            WindowUtil.setBackgroundAlpha(mContext, 0.5f);
        }
        return this;
    }

    /**
     * 根据id设置焦点监听
     *
     * @param viewid
     * @param listener
     */
    public void setOnFocusListener(int viewid, View.OnFocusChangeListener listener) {
        View view = getItemView(viewid);
        view.setOnFocusChangeListener(listener);
    }

    /**
     * builder 类
     */
    public static class Builder {
        private int contentviewid;
        private int width;
        private int height;
        private boolean fouse;
        private boolean outsidecancel;
        private int animstyle;

        public Builder(Activity context) {
            mContext = context;
        }

        public Builder setContentView(int contentviewid) {
            this.contentviewid = contentviewid;
            return this;
        }

        public Builder setwidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setheight(int height) {
            this.height = height;
            return this;
        }

        public Builder setFouse(boolean fouse) {
            this.fouse = fouse;
            return this;
        }

        public Builder setOutSideCancel(boolean outsidecancel) {
            this.outsidecancel = outsidecancel;
            return this;
        }

        public Builder setAnimationStyle(int animstyle) {
            this.animstyle = animstyle;
            return this;
        }

        public CustomPopupWindow builder() {
            return new CustomPopupWindow(this);
        }
    }
}