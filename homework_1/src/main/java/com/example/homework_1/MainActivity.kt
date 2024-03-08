package com.example.homework_1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_1.databinding.FirstActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FirstActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        val receivedData = intent.getStringArrayListExtra(GET_KEY)
        receivedData?.let {
            binding.recycler.adapter = ListAdapter(it)
        }
    }

    companion object {
        private const val GET_KEY = "key"
    }
}