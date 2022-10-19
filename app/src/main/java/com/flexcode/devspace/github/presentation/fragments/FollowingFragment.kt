package com.flexcode.devspace.github.presentation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.databinding.FragmentFollowingBinding
import com.flexcode.devspace.github.presentation.adapters.FollowingAdapter
import com.flexcode.devspace.github.presentation.viewmodels.GetFollowingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var followingAdapter: FollowingAdapter
    private val getFollowingViewModel: GetFollowingViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        followingAdapter = FollowingAdapter(
            FollowingAdapter.OnClickListener {
            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = sharedPref.getString(Constants.KEY_GITHUB_USERNAME, "empty")
        getFollowing(username!!)
        buttonsClickListener()
    }

    private fun buttonsClickListener() {
        binding.apply {
            followingBack.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun getFollowing(username: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            getFollowingViewModel.getFollowing(username).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.animationView.isGone = true
                        binding.followingRv.isVisible = true
                        followingAdapter.submitList(result.data)
                        binding.followingRv.adapter = followingAdapter
                    }
                    is Resource.Loading -> {
                        binding.followingRv.isGone = true
                        binding.animationView.isVisible = true
                    }
                    is Resource.Error -> {
                        /**
                         * Show Error
                         */
                        binding.animationView.isGone = true
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
