package com.example.homework_2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

class FlexBoxLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defThemeAttr: Int = 0,
) : ViewGroup(
    context, attrs, defStyleAttr, defThemeAttr
) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxWidth = 0
        var totalHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, totalHeight)

            val lp = child.layoutParams as MarginLayoutParams
            totalHeight += child.measuredHeight + lp.topMargin + lp.bottomMargin
            maxWidth = maxOf(maxWidth, child.measuredWidth + lp.leftMargin + lp.rightMargin)
        }

        maxWidth += paddingLeft + paddingRight
        totalHeight += paddingTop + paddingBottom

        val width = resolveSize(maxWidth, widthMeasureSpec)
        val height = resolveSize(totalHeight, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var childTop = paddingTop

        for (i in childCount - 1 downTo 0) {
            val child = getChildAt(i)
            val lp = child.layoutParams as MarginLayoutParams

            val childBottom = childTop + child.measuredHeight
            val childLeft = paddingLeft + lp.leftMargin
            val childRight = childLeft + child.measuredWidth

            child.layout(childLeft, childTop, childRight, childBottom)

            childTop = childTop - lp.bottomMargin - lp.topMargin - child.measuredHeight
        }
    }



    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}
