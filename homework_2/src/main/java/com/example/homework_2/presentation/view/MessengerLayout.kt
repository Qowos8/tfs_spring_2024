package com.example.homework_2.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.example.homework_2.R

class MessengerLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defThemeAttr: Int = 0,
) :
    ViewGroup(context, attributeSet, defStyleAttr, defThemeAttr) {

    private var layoutGravity: Int = Gravity.START

    var avatar: View? = null

    var message: LinearLayout? = null

    var flexBoxLayout: FlexBoxLayout? = null

    init {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.MessengerLayout)
        layoutGravity = attrs.getInt(R.styleable.MessengerLayout_layout_gravity, Gravity.START)
        attrs.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        avatar = findViewById(R.id.avatar)
        flexBoxLayout = findViewById(R.id.flex)
        message = findViewById(R.id.linear_layout)
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

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val avatarView = avatar
        val messageLayout = message

        if (avatarView != null) {
            val avatarLeft = paddingLeft + avatarView.marginLeft
            val avatarTop = paddingTop + avatarView.marginTop
            val avatarRight = avatarLeft + avatarView.measuredWidth
            val avatarBottom = avatarTop + avatarView.measuredHeight

            avatarView.layout(
                avatarLeft,
                avatarTop,
                avatarRight,
                avatarBottom
            )
        }

        val messageLeft: Int
        val messageRight: Int

        if (layoutGravity == Gravity.END) {
            messageRight = right - paddingRight - (messageLayout?.marginRight ?: 0)
            messageLeft = messageRight - (messageLayout?.measuredWidth ?: 0)
        } else {
            if (avatarView != null) {
                messageLeft = (avatar?.right ?: 0) + avatarView.marginRight +
                        (messageLayout?.marginLeft ?: 0)
                messageRight = messageLeft + (messageLayout?.measuredWidth ?: 0)
            } else {
                messageRight = right - paddingRight - (messageLayout?.marginRight ?: 0)
                messageLeft = messageRight - (messageLayout?.measuredWidth ?: 0)
            }
        }

        val messageTop = paddingTop + (messageLayout?.marginTop ?: 0)
        val messageBottom = messageTop + (messageLayout?.measuredHeight ?: 0)

        messageLayout?.layout(
            messageLeft,
            messageTop,
            messageRight,
            messageBottom
        )

        val flexBoxLayoutLeft = paddingLeft
        val flexBoxLayoutTop = maxOf(
            messageBottom + (messageLayout?.marginBottom ?: 0),
            (avatar?.bottom ?: 0) + (avatar?.marginBottom ?: 0)
        )
        val flexBoxLayoutRight = right - left - paddingRight
        val flexBoxLayoutBottom = bottom - top - paddingBottom

        flexBoxLayout?.layout(
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
