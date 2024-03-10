package com.example.homework_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.homework_1.databinding.SecondActivityBinding
import android.Manifest
import android.app.Activity
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.homework_1.KeyModule.ACTION_NAME
import com.example.homework_1.KeyModule.DATA_KEY

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: SecondActivityBinding
    private lateinit var localBroadcastManager: LocalBroadcastManager

    private val CALENDAR_PERMISSION_REQUEST_CODE = 100


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val receivedData = intent.getStringArrayListExtra(DATA_KEY)
            if (receivedData != null) {
                openMainActivity(receivedData)
                Log.d("SecondActivity", receivedData.toString())
            } else {
                Log.d("SecondActivity", "data is null")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkCalendarPermission()

        initLocalBroadcastReceiver()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CALENDAR_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCalendarService()
                } else {
                    Toast.makeText(this,"ДЛЯ ПРОДОЛЖЕНИЯ РАЗРЕШИТЕ ДОСТУП К КАЛЕНДАРЮ", LENGTH_SHORT).show()
                    checkCalendarPermission()
                }
                return
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    private fun checkCalendarPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CALENDAR
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CALENDAR),
                CALENDAR_PERMISSION_REQUEST_CODE
            )
        } else {
            startCalendarService()
        }
    }

    private fun initLocalBroadcastReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        val filter = IntentFilter(ACTION_NAME)
        localBroadcastManager.registerReceiver(broadcastReceiver, filter)
    }

    private fun openMainActivity(data: ArrayList<String>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putStringArrayListExtra(DATA_KEY, data)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun startCalendarService() {
        val serviceIntent = Intent(this, CalendarService::class.java)
        startService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(broadcastReceiver)
    }
}