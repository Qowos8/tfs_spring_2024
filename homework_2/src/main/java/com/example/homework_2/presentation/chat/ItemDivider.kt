package com.example.homework_2.presentation.chat

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
import com.example.homework_2.R
import com.example.homework_2.presentation.delegate.MainAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ItemDecoration(
    context: Context,
    resId: Int
) : RecyclerView.ItemDecoration() {

    private val dividerDrawable: Drawable? = ContextCompat.getDrawable(context, resId)
    private val dividerHeight = context.resources.getDimensionPixelSize(R.dimen.divider_height)
    private val dividerWidth = context.resources.getDimensionPixelSize(R.dimen.divider_width)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 24f
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val adapter = parent.adapter as MainAdapter
        if (position == 0 || isNewDay(position, adapter)) {
            outRect.top = dividerHeight
        } else {
            outRect.top = 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter as MainAdapter

        parent.children.forEach { child ->
            val position = parent.getChildAdapterPosition(child)
            if (position == RecyclerView.NO_POSITION || !isNewDay(position, adapter)) return@forEach

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.top - params.topMargin - dividerHeight).toFloat()
            val bottom = top + dividerHeight

            val left = (parent.width - dividerWidth) / 2
            val right = left + dividerWidth

            dividerDrawable?.setBounds(left, top.toInt(), right, bottom.toInt())
            dividerDrawable?.draw(c)

            val date = formatDate(adapter.getMessageTimestamp(position) * 1000L)
            val textWidth = textPaint.measureText(date)
            val textX = (parent.width - textWidth) / 2
            val textY = top + (dividerHeight - (textPaint.descent() + textPaint.ascent())) / 2
            c.drawText(date, textX, textY, textPaint)
        }
    }

    private fun isNewDay(position: Int, adapter: MainAdapter): Boolean {
        if (position == 0) return true

        val currentTimestamp = adapter.getMessageTimestamp(position) * 1000L
        val previousTimestamp = adapter.getMessageTimestamp(position - 1) * 1000L
        val newDay = !isSameDay(currentTimestamp, previousTimestamp)

        return newDay
    }

    private fun isSameDay(timestamp1: Long, timestamp2: Long): Boolean {
        val calendar1 = Calendar.getInstance().apply { timeInMillis = timestamp1 }
        val calendar2 = Calendar.getInstance().apply { timeInMillis = timestamp2 }
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }

    private fun formatDate(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }
}