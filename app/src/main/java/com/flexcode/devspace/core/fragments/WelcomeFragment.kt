package com.flexcode.devspace.core.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.flexcode.devspace.R
import com.flexcode.devspace.core.utils.Constants.KEY_GITHUB_USERNAME
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.databinding.FragmentWelcomeBinding
import com.flexcode.devspace.github.presentation.viewmodels.GetUserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val getUserViewModel: GetUserDetailsViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedPref.getString(KEY_GITHUB_USERNAME,"empty") != "empty"){
            findNavController().navigate(R.id.action_welcomeFragment_to_wakatimeFragment)
        }else{
            listenToClickListeners()
        }
    }

    private fun listenToClickListeners() {
        binding.btnContinue.setOnClickListener {
            it.hideKeyboard()
            val username = binding.etGithubUsername.text.toString().trim()

            binding.animationView.visibility = View.VISIBLE
            searchUserName(username)
        }
    }


    private fun searchUserName(username: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            getUserViewModel.getUserDetails(username).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.animationView.isGone = true
                        if (result.message == null) {
                            sharedPref.edit()
                                .putString(KEY_GITHUB_USERNAME,username)
                                .apply()
                            findNavController().navigate(R.id.action_welcomeFragment_to_wakatimeFragment)
                        }
                    }
                    is Resource.Loading -> {
                        binding.animationView.isVisible = true
                    }
                    is Resource.Error -> {
                        binding.animationView.isGone = true
                        Toast.makeText(
                            requireContext(),
                            "${result.message}", Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
        }
    }



    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}