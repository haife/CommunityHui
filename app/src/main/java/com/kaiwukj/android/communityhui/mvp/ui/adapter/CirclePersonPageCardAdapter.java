package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-29
 * 圈子主页帖子
 */
public class CirclePersonPageCardAdapter extends BaseQuickAdapter<CircleHomeResult, BaseViewHolder> {


    public CirclePersonPageCardAdapter(int layoutResId, @Nullable List<CircleHomeResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleHomeResult item) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        Date strData = null;
        try {
            strData = format.parse(item.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        QMUIRadiusImageView image = helper.getView(R.id.qmui_circle_home_page_image);
        if (null != item.getImgList() && item.getImgList().size() > 0) {
            image.setVisibility(View.VISIBLE);
            GlideArms.with(mContext).load(item.getImgList().get(0)).centerCrop().into(image);
        } else {
            image.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_circle_home_page_title, item.getTitle()).setText(R.id.tv_circle_home_page_content, item.getContent())
                .setText(R.id.tv_circle_home_page_card_topic, Html.fromHtml(String.format(mContext.getString(R.string.person_page_card_hint), strData, item.getNoteType())))
                .setText(R.id.tv_circle_comment_number, String.valueOf(item.getCommentNum()));

        helper.getView(R.id.tv_circle_home_page_card_topic).setOnClickListener(view ->
                ARouter.getInstance().build(ARouterUrlKt.CircleListUrl).withInt(ExtraCons.CIRCLE_TOPIC_TYPE_ID, item.getId())
                        .withString(ExtraCons.CIRCLE_TOPIC_TYPE_TITLE, item.getNoteType()));

    }
}
