package com.corn.project002dating

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.corn.project002dating.auth.IntroActivity
import com.corn.project002dating.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.tabs.TabLayout.LabelVisibility
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navFragment =
            supportFragmentManager.findFragmentById(R.id.control_nav) as NavHostFragment
        val navController = navFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> {
                    navController.navigate(R.id.profilefragment)
                    true
                }

                R.id.nav_chat -> {
                    Log.d("MainActivity", "Nav to chat fragment")
                    navController.navigate(R.id.chatlistfragment)
                    true
                }

                R.id.nav_gather -> {
                    Log.d("MainActivity", "Nav to gather fragment")
                    navController.navigate(R.id.gatherfragment)
                    true
                }

                R.id.nav_mypage -> {
                    navController.navigate(R.id.mypagefragment)
                    true
                }

                else -> false
            }
        }

        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.nav_profile
        }
    }
}