package com.example.homework_2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

class MessengerLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defThemeAttr: Int = 0,
) :
    ViewGroup(context, attributeSet, defStyleAttr, defThemeAttr) {

    var avatar: View? = null
        get() = if (childCount > 0) getChildAt(0) else null

    var name: View? = null
        get() = if (childCount > 1) getChildAt(1) else null

    var message: View? = null
        get() = if (childCount > 2) getChildAt(2) else null

    var flexBoxLayout: FlexBoxLayout? = null
        get() = getChildAt(3) as FlexBoxLayout

    init {
        //inflate(context, R.layout.emoji_field, this)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val avatarView = avatar
        val nameView = name
        val messageView = message
        val avatarLp = nameView!!.layoutParams as MarginLayoutParams
        val nameLp = nameView!!.layoutParams as MarginLayoutParams
        val messageLp = nameView!!.layoutParams as MarginLayoutParams
        val flexLp = nameView!!.layoutParams as MarginLayoutParams

        // Check if any of the views are null
        if (avatarView == null || nameView == null || messageView == null) return

        val avatarLeft = paddingLeft
        val avatarTop = paddingTop
        val avatarRight = avatarLeft + avatarView.measuredWidth
        val avatarBottom = avatarTop + avatarView.measuredHeight
        avatarView.layout(
            avatarLeft + avatarLp.leftMargin   ,
            avatarTop + avatarLp.topMargin   ,
            avatarRight + avatarLp.rightMargin ,
            avatarBottom + avatarLp.topMargin )

        val nameLeft = avatarRight + avatarView.marginRight + nameView.marginLeft
        val nameTop = avatarTop + (avatarView.measuredHeight - nameView.measuredHeight) / 2
        val nameRight = nameLeft + nameView.measuredWidth
        val nameBottom = nameTop + nameView.measuredHeight
        nameView.layout(
            nameLeft + nameLp.leftMargin,
            nameTop + nameLp.topMargin,
            nameRight + nameLp.rightMargin,
            nameBottom + nameLp.bottomMargin)

        val messageLeft = paddingLeft
        val messageTop = maxOf(avatarBottom, nameBottom) + messageView.marginTop
        val messageRight = messageLeft + messageView.measuredWidth
        val messageBottom = messageTop + messageView.measuredHeight
        messageView.layout(
            messageLeft + messageLp.leftMargin,
            messageTop + messageLp.topMargin,
            messageRight + messageLp.rightMargin,
            messageBottom + messageLp.bottomMargin)

        val flexBoxLayoutLeft = paddingLeft
        val flexBoxLayoutTop = messageBottom + messageView.marginBottom
        val flexBoxLayoutRight = right - left - paddingRight
        val flexBoxLayoutBottom = bottom - top - paddingBottom
        flexBoxLayout?.layout(
            flexBoxLayoutLeft, flexBoxLayoutTop,
            flexBoxLayoutRight, flexBoxLayoutBottom
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        var totalWidth = paddingLeft + paddingRight
        var totalHeight = paddingTop + paddingBottom

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as MarginLayoutParams

            totalWidth += child.measuredWidth + lp.leftMargin + lp.rightMargin
            totalHeight += child.measuredHeight + lp.topMargin + lp.bottomMargin
        }

        totalWidth = maxOf(totalWidth, suggestedMinimumWidth)
        totalHeight = maxOf(totalHeight, suggestedMinimumHeight)

        val resolvedWidth = resolveSize(totalWidth, widthMeasureSpec)
        val resolvedHeight = resolveSize(totalHeight, heightMeasureSpec)

        setMeasuredDimension(resolvedWidth, resolvedHeight)
    }


    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}
