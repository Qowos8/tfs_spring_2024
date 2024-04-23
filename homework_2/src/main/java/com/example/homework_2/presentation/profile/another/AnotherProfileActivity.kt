package com.example.homework_2.presentation.profile.another

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.presentation.MainActivity
import com.example.homework_2.presentation.base.ElmBaseActivity
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileActor
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileEffect
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileEvent
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileState
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileStoreFactory
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store

class AnotherProfileActivity : ElmBaseActivity<
        AnotherProfileEvent,
        AnotherProfileEffect,
        AnotherProfileState>() {
    private lateinit var binding: ProfileFragmentBinding
    private var userItem: Int = 0

    override val store: Store<AnotherProfileEvent, AnotherProfileEffect, AnotherProfileState>
        by elmStoreWithRenderer(elmRenderer = this){
            AnotherProfileStoreFactory(AnotherProfileActor()).provide()
        }

    override fun render(state: AnotherProfileState) {
        trackUser(state)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userItem = MainActivity.DataHolder.userData!!
        binding.toolbarProfile.backButton.setOnClickListener {
            finish()
        }
        store.accept(AnotherProfileEvent.Ui.LoadUser(userItem))
    }

    private fun trackUser(state: AnotherProfileState) {
        when (state) {
            is AnotherProfileState.Error -> {
                Log.d("profile", state.error)
            }

            AnotherProfileState.Loading -> {
                showShimmer(binding)
            }

            is AnotherProfileState.Success -> {
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

            AnotherProfileState.Init -> {}
        }
    }

    private fun setStatus(status: Boolean, binding: ProfileFragmentBinding) {
        binding.apply {
            if (!status) {
                isOnline.text = OFFLINE
                isOnline.setTextColor(Color.RED)
            } else {
                isOnline.text = ONLINE
                isOnline.setTextColor(Color.GREEN)
            }
        }
    }

    private fun hideShimmer(binding: ProfileFragmentBinding) {
        binding.apply {
            shimmerContainerUnder.isVisible = false
            shimmerContainerUnder.stopShimmer()
            avatarImage.squareShimmer.isVisible = false
            avatarImage.shimmerContainer.stopShimmer()
        }
    }

    private fun showShimmer(binding: ProfileFragmentBinding) {
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