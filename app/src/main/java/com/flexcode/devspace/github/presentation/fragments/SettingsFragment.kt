package com.flexcode.devspace.github.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.flexcode.devspace.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            settingsBack.setOnClickListener { requireActivity().onBackPressed() }
            layoutTheme.setOnClickListener {
                layoutThemes.isVisible = true
            }
            linearLightTheme.setOnClickListener {
                RadioLightTheme.isChecked = true
                RadioDarkTheme.isChecked = false
                RadioSystemTheme.isChecked = false
            }
            linearDarkTheme.setOnClickListener {
                RadioLightTheme.isChecked = false
                RadioDarkTheme.isChecked = true
                RadioSystemTheme.isChecked = false
            }
            linearSystemTheme.setOnClickListener {
                RadioLightTheme.isChecked = false
                RadioDarkTheme.isChecked = false
                RadioSystemTheme.isChecked = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}