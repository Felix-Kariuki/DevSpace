package com.flexcode.devspace.core.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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

    private val topLevelDestinations: Set<Int>
        get() = setOf(R.id.wakatimeFragment, R.id.quotesFragment, R.id.homeFragment)

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

        // Hides the bottom navigation view
        lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id in topLevelDestinations)
                    binding.bottomNavigationView.isVisible = true
                else binding.bottomNavigationView.isGone = true
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
