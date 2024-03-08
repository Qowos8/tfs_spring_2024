package com.example.homework_1

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_1.databinding.SecondActivityBinding


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: SecondActivityBinding

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val receivedData = intent.getStringArrayListExtra(DATA_KEY)
            openMainActivity(receivedData!!)
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startCalendarService()
        val filter = IntentFilter(ACTION_NAME)
        registerReceiver(broadcastReceiver, filter)
    }

    private fun openMainActivity(data: ArrayList<String>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(PUT_KEY, data)
        startActivity(intent)
    }

    private fun startCalendarService() {
        val serviceIntent = Intent(this, CalendarService::class.java)
        startService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    companion object {
        private const val ACTION_NAME = "CalendarEvents"
        private const val PUT_KEY = "key"
        private const val DATA_KEY = "data_key"
    }
}