package com.flexcode.devspace.github.presentation.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.flexcode.devspace.R
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.databinding.FragmentHomeBinding
import com.flexcode.devspace.github.presentation.adapters.RepositoriesAdapter
import com.flexcode.devspace.github.presentation.viewmodels.GetReposViewModel
import com.flexcode.devspace.github.presentation.viewmodels.GetUserDetailsViewModel
import com.flexcode.devspace.core.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val getUserDetailsViewModel : GetUserDetailsViewModel by viewModels()
    private val getReposViewModel : GetReposViewModel by viewModels()
    private lateinit var adapter: RepositoriesAdapter

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        adapter = RepositoriesAdapter(RepositoriesAdapter.OnClickListener{
            /**
             * todo: navigate to details
             */
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = sharedPref.getString(Constants.KEY_GITHUB_USERNAME,"empty")
        getUserDetails(username!!)
        getUserRepositories(username)
        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            tvlink.setOnClickListener {
                val link = binding.tvlink.text.toString()
                val bundle = Bundle().apply {
                    putSerializable("webLink",link)
                }
                findNavController().navigate(R.id.action_homeFragment_to_webViewFragment,bundle)
            }
            twitter.setOnClickListener {
                val link = "https://www.twitter.com/${binding.twitter.text}"
                val bundle = Bundle().apply {
                    putSerializable("webLink",link)
                }
                findNavController().navigate(R.id.action_homeFragment_to_webViewFragment,bundle)
            }
            ivSettings.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }
            ivShare.setOnClickListener {
                share("Share DevSpace","")
            }
            textView1.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_followersFragment)
            }
            textView2.setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_followingFragment)
            }
        }
    }

    private fun getUserDetails(username:String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            getUserDetailsViewModel.getUserDetails("Felix-Kariuki").collectLatest{ response ->
                when(response){
                    is Resource.Success -> {
                        binding.apply {
                            animationView.isGone = true
                            layoutOne.isVisible = true
                            layoutTwo.isVisible = true

                            Glide.with(requireContext()).load(response.data?.avatar_url).into(ivUserProfile)
                            tvBio.text = response.data?.bio
                            if (response.data?.email.isNullOrEmpty()){
                                ivEmail.isGone = true
                                tvEmail.isGone = true
                            }else {
                                tvEmail.text = response.data?.email
                            }
                            tvlocation.text = response.data?.location
                            tvlink.text = response.data?.blog
                            twitter.text = response.data?.twitter_username
                            tvUserNmae.text = response.data?.name
                            tvName.text = response.data?.login
                            tvFollowers.text = response.data?.followers.toString()
                            tvFollowing.text = response.data?.following.toString()
                            tvRepositories.text = response.data?.public_repos.toString()
                        }
                    }

                    is Resource.Loading -> {
                        binding.apply {
                            animationView.isVisible = true
                            layoutOne.isGone = true
                            layoutTwo.isGone = true
                        }
                    }

                    is Resource.Error -> {
                        binding.apply {
                            animationView.isGone = true
                            layoutOne.isVisible = true
                            layoutTwo.isVisible = true
                        }
                    }

                }
            }
        }



    }


    private fun getUserRepositories(username: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            getReposViewModel.getUserRepositories(username).collectLatest{ response ->
                when(response){
                    is Resource.Success -> {
//                        binding.animationView.isGone = true
                        binding.reposRecyclerView.isVisible = true
                        adapter.submitList(response.data)
                        binding.reposRecyclerView.adapter = adapter
                    }
                    is Resource.Loading -> {
//                        binding.animationView.isVisible = true
                        binding.reposRecyclerView.isGone = true
                    }
                    is Resource.Error -> {
                        /**
                         * Show Error
                         */
//                        binding.animationView.isGone = true
                        binding.reposRecyclerView.isVisible = true
                    }
                }
            }
        }
    }



    private fun share(messageToShare: String, appUrl: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, messageToShare + appUrl)
        startActivity(Intent(intent))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}