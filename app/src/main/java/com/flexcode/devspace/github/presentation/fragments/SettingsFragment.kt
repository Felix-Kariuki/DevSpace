package com.flexcode.devspace.github.presentation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.flexcode.devspace.R
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPref: SharedPreferences

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
            tvChangeAccount.setOnClickListener { navigateToWelcomeScreen() }
            layoutTheme.setOnClickListener { layoutThemes.isVisible = true }
            linearLightTheme.setOnClickListener { setLightTheme() }
            linearDarkTheme.setOnClickListener { setDarkTheme() }
            linearSystemTheme.setOnClickListener { setFollowSystemTheme() }
        }
    }

    private fun setFollowSystemTheme() {
        binding.apply {
            RadioLightTheme.isChecked = false
            RadioDarkTheme.isChecked = false
            RadioSystemTheme.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            sharedPref.edit().putBoolean(Constants.KEY_DARK_THEME,false).apply()
            sharedPref.edit().putBoolean(Constants.KEY_LIGHT_THEME,false).apply()
        }
    }

    private fun setDarkTheme() {
        binding.apply {
            RadioLightTheme.isChecked = false
            RadioDarkTheme.isChecked = true
            RadioSystemTheme.isChecked = false
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        sharedPref.edit().putBoolean(Constants.KEY_DARK_THEME,true).apply()
        sharedPref.edit().putBoolean(Constants.KEY_LIGHT_THEME,false).apply()
    }

    private fun setLightTheme() {
        binding.apply {
            RadioLightTheme.isChecked = true
            RadioDarkTheme.isChecked = false
            RadioSystemTheme.isChecked = false
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        sharedPref.edit().putBoolean(Constants.KEY_LIGHT_THEME,true).apply()
        sharedPref.edit().putBoolean(Constants.KEY_DARK_THEME,false).apply()
    }

    private fun navigateToWelcomeScreen() {
        sharedPref.edit().putString(Constants.KEY_GITHUB_USERNAME,"empty").apply()
        findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}