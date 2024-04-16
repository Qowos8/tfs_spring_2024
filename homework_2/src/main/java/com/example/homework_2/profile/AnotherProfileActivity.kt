package com.example.homework_2.profile

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.MainActivity
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.people.PeopleItem

class AnotherProfileActivity : AppCompatActivity() {
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var userItem: ProfileItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userItem = MainActivity.DataHolder.userData!!
        binding.apply {
            userName.text = userItem.name
            logoutButton.visibility = View.GONE
            if (userItem.isActive) {
                isOnline.text = STATUS
                isOnline.setTextColor(Color.RED)
            }
        }
        binding.toolbarProfile.backButton.setOnClickListener {
            finish()
        }
    }

    private companion object{
        private const val STATUS = "Offline"
    }
}