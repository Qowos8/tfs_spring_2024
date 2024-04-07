package com.example.homework_2.utils

import android.view.View
import android.view.ViewGroup

object ViewUtils {
    fun <T : View> removeView(view: T, binding: ViewGroup) {
        binding.removeView(view)
    }
}