package com.example.homework_2.profile

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.people.PeopleItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        binding.toolbarProfile.toolbar.visibility = View.GONE
        binding.toolbarProfile.backButton.visibility= View.GONE
        viewModel.getUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProfile()
    }

    private fun setProfile(){
        lifecycleScope.launch {
            viewModel.profileState.collect{ state ->
                when(state){
                    is ProfileState.Error -> {
                        Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                    }
                    ProfileState.Loading -> {
                        binding.apply {
                            avatarImage.squareShimmer.isVisible = true
                            shimmerContainerUnder.isVisible = true
                            shimmerContainerUnder.startShimmer()
                            avatarImage.shimmerContainer.startShimmer()
                        }
                    }
                    is ProfileState.Success -> {
                        setResources(state.profileData)
                        binding.apply {
                            shimmerContainerUnder.isVisible = false
                            shimmerContainerUnder.stopShimmer()
                            avatarImage.squareShimmer.isVisible = false
                            avatarImage.shimmerContainer.stopShimmer()
                        }
                    }
                }
            }
        }
    }

    private fun setResources(profileData: ProfileItem){
        binding.apply {
            userName.text = profileData.name
            isOnline.text = profileData.isActive.toString()
            Glide.with(requireContext())
                .load(profileData.url)
                .override(Target.SIZE_ORIGINAL)
                .override(LayoutParams.MATCH_PARENT)
                .into(avatarImage.squareCardImage)
        }
    }
}