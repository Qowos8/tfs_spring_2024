package com.example.homework_2.presentation.profile.me

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.homework_2.R
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.presentation.base.ElmBaseFragment
import com.example.homework_2.presentation.profile.di.ProfileComponent
import com.example.homework_2.presentation.profile.me.mvi.ProfileEffect
import com.example.homework_2.presentation.profile.me.mvi.ProfileEvent
import com.example.homework_2.presentation.profile.me.mvi.ProfileState
import com.example.homework_2.presentation.profile.me.mvi.ProfileStoreFactory
import com.google.android.material.snackbar.Snackbar
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ProfileFragment : ElmBaseFragment<
        ProfileEvent,
        ProfileEffect,
        ProfileState>(R.layout.profile_fragment) {

    @Inject
    lateinit var factory: ProfileStoreFactory

    private lateinit var binding: ProfileFragmentBinding

    override val store: Store<ProfileEvent, ProfileEffect, ProfileState>
            by elmStoreWithRenderer(elmRenderer = this) {
                factory.provide()
            }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProfileComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        binding.toolbarProfile.toolbarProfile.isVisible = false
        binding.toolbarProfile.backButton.isVisible = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.accept(ProfileEvent.Ui.Init)
    }

    override fun render(state: ProfileState) {
        setProfile(state)
    }

    private fun setProfile(state: ProfileState) {
        when (state) {
            is ProfileState.Error -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
                Log.d("profile", state.error)
            }

            ProfileState.Loading -> {
                showShimmer()
                store.accept(ProfileEvent.Ui.LoadUser)
            }

            is ProfileState.Success -> {
                setResources(state.profileData)
                hideShimmer()
            }

            ProfileState.Init -> {}

            is ProfileState.CacheSuccess -> {
                setResources(state.profileData)
                hideShimmer()
                store.accept(ProfileEvent.Ui.LoadUser)
            }
        }
    }


    private fun setResources(profileData: ProfileItem) {
        binding.apply {
            userName.text = profileData.name
            isOnline.text = profileData.isActive.toString()
            Glide.with(requireContext())
                .load(profileData.url)
                .override(LayoutParams.MATCH_PARENT)
                .into(avatarImage.squareCardImage)
        }
    }

    private fun showShimmer(){
        binding.apply {
            avatarImage.squareShimmer.isVisible = true
            shimmerContainerUnder.isVisible = true
            shimmerContainerUnder.startShimmer()
            avatarImage.shimmerContainer.startShimmer()
        }
    }

    private fun hideShimmer(){
        binding.apply {
            shimmerContainerUnder.isVisible = false
            shimmerContainerUnder.stopShimmer()
            avatarImage.squareShimmer.isVisible = false
            avatarImage.shimmerContainer.stopShimmer()
        }
    }
}