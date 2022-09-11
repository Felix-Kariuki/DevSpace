package com.flexcode.devspace.core.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.flexcode.devspace.R
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    @Inject
    lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkTheme()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as
                NavHostFragment
        navController = navHostFragment.findNavController()

        binding.bottomNavigationView.apply { setupWithNavController(navController) }


        //hide bottom nav view
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingsFragment -> binding.bottomNavigationView.isGone = true
                R.id.followersFragment -> binding.bottomNavigationView.isGone = true
                R.id.followingFragment -> binding.bottomNavigationView.isGone = true
                R.id.welcomeFragment -> binding.bottomNavigationView.isGone = true
                else -> binding.bottomNavigationView.isVisible = true
            }
        }

    }

    private fun checkTheme() {
        if (sharedPref.getBoolean(Constants.KEY_DARK_THEME, true)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else if (sharedPref.getBoolean(Constants.KEY_LIGHT_THEME, true)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

}