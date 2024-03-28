package com.example.homework_2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.homework_2.utils.SpUtils.sp

class EmojiCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defThemeAttr: Int = 0,
    ) : View(context, attrs, defStyleAttr, defThemeAttr) {

    var emoji: String = "😃"
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }
    var reactionCount: Int = 0
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    private val textToDraw
        get() = "$emoji $reactionCount"

    private val rectangle = Rect()

    private val textPaint = TextPaint().apply {
        color = Color.WHITE
        textSize = 24f.sp(context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        textPaint.getTextBounds(textToDraw, 0, textToDraw.length, rectangle)

        val resolveWidth =
            resolveSize(paddingRight + paddingLeft +
                    rectangle.width(), widthMeasureSpec)
        val resolveHeight =
            resolveSize(paddingTop + paddingBottom +
                    rectangle.height(), heightMeasureSpec)

        setMeasuredDimension(resolveWidth, resolveHeight)
    }


    override fun onDraw(canvas: Canvas) {
        val topOffset = height / 2 - rectangle.exactCenterY()
        canvas.drawText(textToDraw, paddingLeft.toFloat(), topOffset, textPaint)
    }
}