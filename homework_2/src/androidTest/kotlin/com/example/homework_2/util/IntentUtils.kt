package com.example.homework_2.util

import android.content.Intent
import androidx.test.core.app.ApplicationProvider

object IntentUtils {
     inline fun<reified T> getIntent(screen: T): Intent {
        return Intent(ApplicationProvider.getApplicationContext(), T::class.java)
    }
}