package com.example.homework_2.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

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
        val currentRowViews = mutableListOf<View>()

        var totalHeight = paddingTop + paddingBottom

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                totalHeight
            )
            val lp = child.layoutParams as MarginLayoutParams

            if (currentRowWidth > 0 && currentRowWidth + lp.leftMargin + lp.rightMargin + child.measuredWidth > parentWidth - paddingLeft - paddingRight) {
                viewsInRow.add(currentRowViews.toList())
                currentRowViews.clear()
                currentRowWidth = 0
                totalHeight += currentRowHeight
                currentRowHeight = child.measuredHeight + lp.topMargin + lp.bottomMargin
            } else {
                currentRowHeight = maxOf(
                    currentRowHeight,
                    child.measuredHeight + lp.topMargin + lp.bottomMargin
                )
            }
            currentRowViews.add(child)
            currentRowWidth += child.measuredWidth + lp.leftMargin + lp.rightMargin
        }

        viewsInRow.add(currentRowViews.toList())

        val totalWidth = currentRowWidth + paddingLeft + paddingRight
        totalHeight += currentRowHeight

        setMeasuredDimension(
            resolveSizeAndState(totalWidth, widthMeasureSpec, 0),
            resolveSizeAndState(totalHeight, heightMeasureSpec, 0)
        )
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

            val childRightWithMargins = childLeft + lp.leftMargin + child.measuredWidth
            val childBottomWithMargins = childTop + lp.topMargin + child.measuredHeight

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
