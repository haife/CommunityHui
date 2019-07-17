package com.kaiwukj.android.communityhui.mvp.ui.widget.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaiwukj.android.communityhui.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc $desc
 */
public class HorizontalMessageWidget extends RelativeLayout {

    @BindView(R.id.tv_left_content)
    TextView mLeftTv;
    @BindView(R.id.tv_right_content)
    TextView mRightIv;

    public HorizontalMessageWidget(Context context) {
        super(context);
        init(context, null);
    }

    public HorizontalMessageWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attr) {
        View widgetView = LayoutInflater.from(context).inflate(R.layout.custom_horizontal_message_layout, this);
        ButterKnife.bind(this, widgetView);
        TypedArray array = context.obtainStyledAttributes(attr, R.styleable.TextInfoWidget);
        //布局类型 比如是姓名、手机等。不可为空 如果使用该组件必须要传

        String leftStr = array.getString(R.styleable.TextInfoWidget_left_str);
        String rightStr = array.getString(R.styleable.TextInfoWidget_right_str);
        mLeftTv.setText(leftStr);
        mRightIv.setText(rightStr);
        array.recycle();
    }


    public void setRightStr(@NonNull String content) {
        mRightIv.setText(content);
    }

}
