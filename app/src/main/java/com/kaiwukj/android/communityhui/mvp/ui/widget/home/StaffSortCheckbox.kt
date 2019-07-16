package com.kaiwukj.android.communityhui.mvp.ui.widget.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import com.kaiwukj.android.communityhui.R

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc $desc
 */
class StaffSortCheckbox : AppCompatCheckBox {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val states = intArrayOf(R.attr.sort_desc_state)
    private var isMiddle: Boolean = false
    private var mBroadcasting: Boolean = false
    private var onStateChangeListener: OnStateChangeListener? = null

    private val checkDrawable: Drawable = ContextCompat.getDrawable(context, R.drawable.ic_house_keeping_staff_ascending)!!
    private val unCheckDrawable = ContextCompat.getDrawable(context, R.drawable.ic_house_keeping_staff_normal)!!
    private val middleDrawable = ContextCompat.getDrawable(context, R.drawable.ic_house_keeping_staff_descending)!!

    init {
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(states, middleDrawable)
        stateListDrawable.addState(intArrayOf(android.R.attr.state_checked), checkDrawable)
        stateListDrawable.addState(intArrayOf(), unCheckDrawable)
        stateListDrawable.setBounds(0, 0, stateListDrawable.minimumWidth, stateListDrawable.minimumHeight)
        setCompoundDrawables(stateListDrawable, null, null, null)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        buttonDrawable = StateListDrawable()
    }

    override fun toggle() {
        if (isMiddle) {
            isChecked = true
        } else {
            super.toggle()
        }
    }

    override fun setChecked(checked: Boolean) {
        val checkedChanged = isChecked != checked
        super.setChecked(checked)
        val wasMiddle = isMiddle
        setMiddleState(isMiddle = false, notify = false)
        if (wasMiddle || checkedChanged) {
            notifyStateListener()
        }
    }

    /**
     * 设置中间状态
     */
    private fun setMiddleState(indeterminate: Boolean) {
        setMiddleState(indeterminate, true)
    }

    private fun getState(): Boolean? {
        return if (isMiddle) null else isChecked
    }

    /**
     * 设置状态，null表示中间状态
     */
    fun setState(state: Boolean?) {
        if (state != null) {
            isChecked = state
        } else {
            setMiddleState(true)
        }
    }

    private fun setMiddleState(isMiddle: Boolean, notify: Boolean) {
        if (this.isMiddle != isMiddle) {
            this.isMiddle = isMiddle
            refreshDrawableState()
            if (notify) {
                notifyStateListener()
            }
        }
    }

    private fun notifyStateListener() {
        if (mBroadcasting) {
            return
        }
        mBroadcasting = true
        if (onStateChangeListener != null) {
            onStateChangeListener!!.onStateChanged(this, getState())
        }
        mBroadcasting = false
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (getState() == null) {
            View.mergeDrawableStates(drawableState, states)
        }
        return drawableState
    }

    fun setOnStateChangeListener(listener: OnStateChangeListener) {
        this.onStateChangeListener = listener
    }

    interface OnStateChangeListener {
        fun onStateChanged(checkbox: StaffSortCheckbox, newState: Boolean?)
    }
}