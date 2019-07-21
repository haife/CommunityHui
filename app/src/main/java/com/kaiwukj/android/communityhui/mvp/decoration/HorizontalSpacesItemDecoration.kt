package com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * TODO：水平方向的RecycleView分割线
 */
class HorizontalSpacesItemDecoration(val spaceItem: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when {
            parent.getChildLayoutPosition(view) == 0 -> {
                outRect.left = spaceItem + 26
                outRect.right = spaceItem
            }
            parent.getChildLayoutPosition(view) == parent.adapter?.itemCount?.minus(1) -> {
                outRect.right = spaceItem + 26
            }
            else -> outRect.right = spaceItem
        }
    }

}