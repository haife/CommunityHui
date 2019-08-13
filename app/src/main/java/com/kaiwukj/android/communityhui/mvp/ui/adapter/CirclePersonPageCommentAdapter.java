package com.kaiwukj.android.communityhui.mvp.ui.adapter;

import android.content.Context;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.PersonPageCardCommentResult;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-29
 * 圈子主页帖子
 */
public class CirclePersonPageCommentAdapter extends BaseQuickAdapter<PersonPageCardCommentResult, BaseViewHolder> {


    public CirclePersonPageCommentAdapter(int layoutResId, @Nullable List<PersonPageCardCommentResult> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonPageCardCommentResult item) {
        helper.setText(R.id.tv_circle_person_reply_content, item.getContent()).setText(R.id.tv_circle_person_reply_time, item.getReplyCreateTime())
                .setText(R.id.tv_circle_person_reply_topic, Html.fromHtml(String.format(mContext.getString(R.string.person_page_card_topic_hint), item.getTitle())));

        helper.addOnClickListener(R.id.tv_circle_person_reply_topic);


    }
}
