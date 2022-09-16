package com.flexcode.devspace.wakatime.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flexcode.devspace.R
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.databinding.FragmentWakatimeBinding
import com.flexcode.devspace.wakatime.presentation.adapters.EditorsAdapter
import com.flexcode.devspace.wakatime.presentation.viewmodels.GetEditorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class WakatimeFragment : Fragment(R.layout.fragment_wakatime) {

    private var _binding: FragmentWakatimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var editorsAdapter: EditorsAdapter
    private val getEditorsViewModel : GetEditorsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWakatimeBinding.inflate(inflater,container,false)


        editorsAdapter = EditorsAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEditors()
        exitApp()
    }

    private fun getEditors() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            val token = "mF_9.B5f-4.1JqM"
            getEditorsViewModel.getAllEditors(token ="Bearer $token").collect{result->
                when(result){
                    is Resource.Success->{
                        editorsAdapter.submitList(result.data)
                        binding.apply {
                            rvEditors.adapter = editorsAdapter
                        }
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Loading-> {

                    }
                }
            }
        }
    }

    private fun exitApp() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}