package com.example.homework_1

import android.annotation.SuppressLint
import android.app.Service
import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.provider.CalendarContract
import android.util.Log

class CalendarService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("Range")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val projection = CalendarContract.Events.TITLE

        val cursor = contentResolver.query(
            CalendarContract.Events.CONTENT_URI,
            arrayOf(projection),
            null,
            null,
            null
        )
        val eventDataList = ArrayList<String>()
        cursor?.use {
            while (it.moveToNext()) {
                val title = it.getString(it.getColumnIndex(CalendarContract.Events.TITLE))
                eventDataList.add(title)
            }
        }
        sendEvents(eventDataList)
        return START_NOT_STICKY
    }

    private fun sendEvents(data: ArrayList<String>) {
        val intent = Intent(ACTION_NAME)
        intent.putStringArrayListExtra(DATA_KEY, data)
        sendBroadcast(intent)
    }

    companion object {
        private const val ACTION_NAME = "CalendarEvents"
        private const val DATA_KEY = "data_key"
    }
}