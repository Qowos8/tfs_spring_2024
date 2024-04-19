package com.example.homework_2.presentation.profile

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_2.presentation.MainActivity
import com.example.homework_2.databinding.ProfileFragmentBinding
import kotlinx.coroutines.launch

class AnotherProfileActivity : AppCompatActivity() {
    private lateinit var binding: ProfileFragmentBinding
    private var userItem: Int = 0
    private val viewModel: AnotherProfileActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userItem = MainActivity.DataHolder.userData!!
        viewModel.getUser(userItem)

        binding.toolbarProfile.backButton.setOnClickListener {
            finish()
        }
        trackUser()
    }

    private fun trackUser() {
        lifecycleScope.launch {
            viewModel.userState.collect { state ->
                when (state) {
                    is UserState.Error -> {
                        Log.d("profile", state.error)
                    }

                    UserState.Loading -> {
                        showShimmer(binding)
                    }

                    is UserState.Success -> {
                        binding.apply {
                            userName.text = state.profileData.name
                            Glide.with(avatarImage.squareCardImage)
                                .load(state.profileData.url)
                                .override(Target.SIZE_ORIGINAL)
                                .override(ActionBar.LayoutParams.MATCH_PARENT)
                                .into(avatarImage.squareCardImage)
                            setStatus(state.profileData.isActive, this)
                            hideShimmer(this)
                        }
                    }
                }
            }
        }
    }

    private fun setStatus(status: Boolean, binding: ProfileFragmentBinding){
        binding.apply {
            if (!status) {
                isOnline.text = OFFLINE
                isOnline.setTextColor(Color.RED)
            }
            else{
                isOnline.text = ONLINE
                isOnline.setTextColor(Color.GREEN)
            }
        }
    }

    private fun hideShimmer(binding: ProfileFragmentBinding){
        binding.apply {
            shimmerContainerUnder.isVisible = false
            shimmerContainerUnder.stopShimmer()
            avatarImage.squareShimmer.isVisible = false
            avatarImage.shimmerContainer.stopShimmer()
        }
    }

    private fun showShimmer(binding: ProfileFragmentBinding){
        binding.apply {
            avatarImage.squareShimmer.isVisible = true
            shimmerContainerUnder.isVisible = true
            shimmerContainerUnder.startShimmer()
            avatarImage.shimmerContainer.startShimmer()
        }
    }
    private companion object {
        private const val OFFLINE = "Offline"
        private const val ONLINE = "Online"
    }
}