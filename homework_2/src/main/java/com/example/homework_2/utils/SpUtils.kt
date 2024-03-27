package com.example.homework_2.utils

import android.content.Context
import android.util.TypedValue

object SpUtils {
    fun Float.sp(context: Context) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics
    )
}