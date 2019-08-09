package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.os.Bundle;

import com.kaiwukj.android.communityhui.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public class WebActivity extends SwipeBackActivity {
    @BindView(R.id.qmui_web_view)
    QMUIWebView mWebView;
    @BindView(R.id.qtb_web_view)
    QMUITopBar mTopBar;
    public String url = "http://www.kaiwumace.com/treaty/service/service.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        mTopBar.setTitle("用户协议");
        mTopBar.addLeftBackImageButton().setOnClickListener(view -> onBackPressedSupport());
        mWebView.loadUrl(url);
    }
}
