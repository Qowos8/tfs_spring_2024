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

    val avatar: View?
        get() = if (childCount > 0)
            getChildAt(0)
        else null

    val name: View?
        get() = if (childCount > 1)
            getChildAt(1)
        else null

    val message: View?
        get() = if (childCount > 2)
            getChildAt(2)
        else null

    val flexBoxLayout: FlexBoxLayout
        get() = getChildAt(3) as FlexBoxLayout

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

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val avatarView = avatar
        val nameView = name
        val messageView = message

        val avatarLeft = paddingLeft + avatarView!!.marginLeft
        val avatarTop = paddingTop + avatarView.marginTop
        val avatarRight = avatarLeft + avatarView.measuredWidth
        val avatarBottom = avatarTop + avatarView.measuredHeight

        avatarView.layout(
            avatarLeft,
            avatarTop,
            avatarRight,
            avatarBottom
        )

        val nameLeft = avatarRight + avatarView.marginRight + nameView!!.marginLeft
        val nameTop = paddingTop + nameView.marginTop
        val nameRight = nameLeft + nameView.measuredWidth
        val nameBottom = nameTop + nameView.measuredHeight

        nameView.layout(
            nameLeft,
            nameTop,
            nameRight,
            nameBottom
        )

        val messageLeft = nameLeft + nameView.marginRight
        val messageTop = nameBottom + nameView.marginBottom + messageView!!.marginTop
        val messageRight = messageLeft + messageView.measuredWidth
        val messageBottom = messageTop + messageView.measuredHeight

        messageView.layout(
            messageLeft,
            messageTop,
            messageRight,
            messageBottom
        )

        val flexBoxLayoutLeft = paddingLeft
        val flexBoxLayoutTop = maxOf(messageBottom + messageView.marginBottom, avatarBottom + avatarView.marginBottom )
        val flexBoxLayoutRight = right - left - paddingRight
        val flexBoxLayoutBottom = bottom - top - paddingBottom

        flexBoxLayout.layout(
            flexBoxLayoutLeft,
            flexBoxLayoutTop,
            flexBoxLayoutRight,
            flexBoxLayoutBottom
        )
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}
