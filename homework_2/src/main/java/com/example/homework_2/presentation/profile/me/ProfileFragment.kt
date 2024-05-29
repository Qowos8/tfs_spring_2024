package com.example.homework_2.presentation.profile.me

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.presentation.profile.di.ProfileComponent
import com.example.homework_2.presentation.profile.me.mvi.ProfileState
import com.example.homework_2.presentation.profile.me.mvi.ProfileStoreFactory
import com.example.homework_2.presentation.profile.me.state_holder.ProfileViewModel
import com.example.homework_2.presentation.profile.me.state_holder.ProfileViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var factory: ProfileStoreFactory
    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory

    private lateinit var binding: ProfileFragmentBinding
    private val viewModel: ProfileViewModel by viewModels {
        viewModelFactory
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
        binding.hideToolBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProfileState()
    }

    private fun render(state: ProfileState) {
        when (state) {
            ProfileState.Init -> {
                binding.showShimmer()
                viewModel.updateProfile()
            }
            is ProfileState.Error -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
                binding.hideShimmer()
            }
            ProfileState.Loading -> binding.showShimmer()
            is ProfileState.CacheSuccess -> {
                binding.setResources(state.profileData)
                binding.hideShimmer()
            }
            ProfileState.CacheEmpty -> {
                viewModel.updateProfile()
            }
            ProfileState.CacheLoaded -> {
                viewModel.loadProfile()
            }
        }
    }

    private fun observeProfileState() {
        lifecycleScope.launch {
            viewModel.profileState.collect { state ->
                render(state)
            }
        }
    }

    private fun ProfileFragmentBinding.setResources(profileData: ProfileItem) {
        userName.text = profileData.name
        isOnline.text =
            if (profileData.isActive) ONLINE
            else OFFLINE

        Glide.with(requireContext())
            .load(profileData.url)
            .override(LayoutParams.MATCH_PARENT)
            .into(avatarImage.squareCardImage)
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

    private fun ProfileFragmentBinding.hideToolBar(){
        toolbarProfile.toolbarProfile.isVisible = false
        toolbarProfile.backButton.isVisible = false
    }

    private companion object {
        private const val ONLINE = "Online"
        private const val OFFLINE = "Offline"
    }
}