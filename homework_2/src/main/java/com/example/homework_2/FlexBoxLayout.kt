package com.example.homework_2

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.content.withStyledAttributes
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

    private val viewsInRow = mutableListOf<List<View>>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        viewsInRow.clear()

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        var currentRowWidth = 0
        var currentRowHeight = 0
        var currentRowViews = mutableListOf<View>()

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val lp = child.layoutParams as MarginLayoutParams

            if (currentRowWidth + child.measuredWidth + lp.leftMargin + lp.rightMargin > parentWidth) {
                viewsInRow.add(currentRowViews.toList())
                currentRowViews.clear()
                currentRowWidth = 0
                currentRowHeight += child.measuredHeight + lp.topMargin + lp.bottomMargin
            }

            currentRowViews.add(child)
            currentRowWidth += child.measuredWidth + lp.leftMargin + lp.rightMargin
            currentRowHeight = maxOf(currentRowHeight, child.measuredHeight + lp.topMargin + lp.bottomMargin)
        }

        viewsInRow.add(currentRowViews.toList())

        val totalHeight = currentRowHeight + paddingTop + paddingBottom
        setMeasuredDimension(parentWidth, totalHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var childTop = paddingTop
        var childLeft = paddingLeft
        var lineHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            val lp = child.layoutParams as MarginLayoutParams

            val childRight = childLeft + child.measuredWidth
            val childBottom = childTop + child.measuredHeight

            if (childRight + lp.rightMargin > right - left - paddingRight) {
                childLeft = paddingLeft
                childTop += lineHeight
                lineHeight = 0
            }

            val childRightWithMargins = childRight + lp.rightMargin
            val childBottomWithMargins = childBottom + lp.bottomMargin

            child.layout(
                childLeft + lp.leftMargin,
                childTop + lp.topMargin,
                childRightWithMargins,
                childBottomWithMargins
            )
            childLeft += child.measuredWidth + lp.leftMargin + lp.rightMargin
            lineHeight = maxOf(lineHeight, child.measuredHeight + lp.topMargin + lp.bottomMargin)
        }
    }



    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}
