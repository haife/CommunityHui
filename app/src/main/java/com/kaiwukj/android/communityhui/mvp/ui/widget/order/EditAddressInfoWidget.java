package com.kaiwukj.android.communityhui.mvp.ui.widget.order;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaiwukj.android.communityhui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/9
 * @desc
 */
public class EditAddressInfoWidget extends LinearLayout {

    @BindView(R.id.tv_widget_staff_message_type)
    TextView mMessageType;
    @BindView(R.id.et_widget_staff_message_input)
    EditText mMessageEt;


    public EditAddressInfoWidget(Context context) {
        super(context);
        init(context, null);
    }

    public EditAddressInfoWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EditAddressInfoWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attr) {
        View widgetView = LayoutInflater.from(context).inflate(R.layout.widget_edit_address_message, this);
        ButterKnife.bind(this, widgetView);
        TypedArray array = context.obtainStyledAttributes(attr, R.styleable.TextInfoWidget);

        String leftStr = array.getString(R.styleable.TextInfoWidget_left_str);
        String rightStr = array.getString(R.styleable.TextInfoWidget_right_str);
        mMessageType.setText(leftStr);
        mMessageEt.setHint(rightStr);
        array.recycle();
    }


}


