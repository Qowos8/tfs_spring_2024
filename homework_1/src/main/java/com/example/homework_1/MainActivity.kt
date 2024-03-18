package com.example.homework_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.homework_1.KeyModule.ACTION_NAME
import com.example.homework_1.KeyModule.DATA_KEY
import com.example.homework_1.databinding.FirstActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FirstActivityBinding
    private lateinit var localBroadcastReceiver: LocalBroadcastManager
    private lateinit var listAdapter: ListAdapter

    private val secondActivityBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.getStringArrayListExtra(DATA_KEY)?.let {
                listAdapter.updateData(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listAdapter = ListAdapter()

        binding.recycler.adapter = listAdapter

        binding.button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        createReceiver()
    }

    private fun createReceiver() {
        val secondActivityIntentFilter = IntentFilter(ACTION_NAME)
        localBroadcastReceiver = LocalBroadcastManager.getInstance(this)
        localBroadcastReceiver.registerReceiver(
            secondActivityBroadcastReceiver,
            secondActivityIntentFilter
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastReceiver.unregisterReceiver(secondActivityBroadcastReceiver)
    }
}

