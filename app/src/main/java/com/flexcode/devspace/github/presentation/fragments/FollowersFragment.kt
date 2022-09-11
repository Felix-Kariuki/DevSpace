package com.flexcode.devspace.github.presentation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.databinding.FragmentFollowersBinding
import com.flexcode.devspace.github.presentation.adapters.FollowersAdapter
import com.flexcode.devspace.github.presentation.viewmodels.GetFollowersViewModel
import com.flexcode.devspace.core.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var followersAdapter: FollowersAdapter
    private val getFollowersViewModel : GetFollowersViewModel by viewModels()


    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater,container,false)

        followersAdapter = FollowersAdapter(FollowersAdapter.OnClickListener{
            /**
             * TODO: Navigate to details
             */
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = sharedPref.getString(Constants.KEY_GITHUB_USERNAME,"empty")
        getFollowers(username!!)
        buttonsClickListener()
    }

    private fun buttonsClickListener() {
        binding.apply {
            followersBack.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun getFollowers(username: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            getFollowersViewModel.getFollowers(username).collectLatest { result ->
                when(result){
                    is Resource.Success -> {
                        binding.animationView.isGone = true
                        binding.reposRecyclerView.isVisible = true
                        followersAdapter.submitList(result.data)
                        binding.reposRecyclerView.adapter = followersAdapter

                    }
                    is Resource.Loading -> {
                        binding.reposRecyclerView.isGone = true
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