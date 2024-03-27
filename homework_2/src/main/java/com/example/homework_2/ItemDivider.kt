package com.example.homework_2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(context: Context, resId: Int, private val date: String) : RecyclerView.ItemDecoration() {

    private var dividerDrawable: Drawable? = ContextCompat.getDrawable(context, resId)
    private val dividerWidth = context.resources.getDimension(R.dimen.divider_width)
    private val dividerHeight = context.resources.getDimension(R.dimen.divider_height)

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        textPaint.color = Color.WHITE
        textPaint.textSize = 24f
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != 0) {
            outRect.top = dividerDrawable?.intrinsicHeight ?: 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        parent.children.forEach { child ->
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = (parent.width - dividerWidth) / 2
            val right = left + dividerWidth
            val top = child.bottom + params.bottomMargin + dividerHeight / 2
            val bottom = top + dividerHeight

            dividerDrawable?.setBounds(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
            dividerDrawable?.draw(c)

            val textWidth = textPaint.measureText(date)
            val textX = (parent.width - textWidth) / 2
            val textY = (top + bottom - textPaint.descent() - textPaint.ascent()) / 2
            c.drawText(date, textX, textY, textPaint)
        }
    }
}