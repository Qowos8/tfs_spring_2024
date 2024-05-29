package com.example.homework_2.presentation.profile.another

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.presentation.MainActivity
import com.example.homework_2.presentation.base.ElmBaseActivity
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileEffect
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileEvent
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileState
import com.example.homework_2.presentation.profile.another.mvi.AnotherProfileStoreFactory
import com.example.homework_2.presentation.profile.di.ProfileComponent
import com.google.android.material.snackbar.Snackbar
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class AnotherProfileActivity : ElmBaseActivity<
        AnotherProfileEvent,
        AnotherProfileEffect,
        AnotherProfileState>() {
    private lateinit var binding: ProfileFragmentBinding

    @Inject
    lateinit var factory: AnotherProfileStoreFactory

    override val store: Store<AnotherProfileEvent, AnotherProfileEffect, AnotherProfileState>
            by elmStoreWithRenderer(elmRenderer = this) {
                factory.provide()
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponent().inject(this)

        binding = ProfileFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarProfile.backButton.setOnClickListener { finish() }
        store.accept(AnotherProfileEvent.Ui.LoadDBUser(MainActivity.DataHolder.userData))
    }

    override fun render(state: AnotherProfileState) {
        when (state) {
            AnotherProfileState.Init -> Unit

            is AnotherProfileState.Error -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
            }

            AnotherProfileState.Loading -> {
                binding.showShimmer()
            }

            is AnotherProfileState.Success -> {
                setResources(state.profileData)
                binding.hideShimmer()
            }
            is AnotherProfileState.CacheSuccess -> {
                setResources(state.profileData)
                store.accept(AnotherProfileEvent.Ui.UpdateUser(MainActivity.DataHolder.userData))
                binding.hideShimmer()
            }
        }
    }

    private fun setResources(profileData: ProfileItem) {
        binding.apply {
            userName.text = profileData.name
            Glide.with(avatarImage.squareCardImage)
                .load(profileData.url)
                .override(ActionBar.LayoutParams.MATCH_PARENT)
                .into(avatarImage.squareCardImage)
            setStatus(profileData.isActive, this)
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

    private fun ProfileFragmentBinding.showShimmer() {
        shimmerContainerUnder.isVisible = true
        shimmerContainerUnder.startShimmer()
        avatarImage.shimmerContainer.startShimmer()
        avatarImage.cardShimmer.isVisible = true
        avatarImage.squareCard.isVisible = false

    }

    private fun ProfileFragmentBinding.hideShimmer() {
        shimmerContainerUnder.isVisible = false
        shimmerContainerUnder.stopShimmer()
        avatarImage.shimmerContainer.stopShimmer()
        avatarImage.cardShimmer.isVisible = false
        avatarImage.squareCard.isVisible = true
    }

    private companion object {
        private const val OFFLINE = "Offline"
        private const val ONLINE = "Online"
    }
}