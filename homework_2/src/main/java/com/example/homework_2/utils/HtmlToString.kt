package com.example.homework_2.utils
import android.text.Html

object HtmlToString {
    fun convertToString(htmlContent: String): String {
        return Html.fromHtml(htmlContent).toString()
    }
}