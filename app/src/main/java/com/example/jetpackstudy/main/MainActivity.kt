package com.example.jetpackstudy.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.jetpackstudy.R
import com.example.jetpackstudy.databinding.ActivityMainBinding
import com.example.jetpackstudy.main.list.ui.ListFragment
import com.example.jetpackstudy.main.search.ui.SearchFragment
import com.example.jetpackstudy.main.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragment1 = SearchFragment()
    private val fragment2 = ListFragment()
    private val fragment3 = SettingFragment()
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.title = "Search"
        supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment1).commitAllowingStateLoss()

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_search -> {
                    supportActionBar?.title = "Search"
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment1)
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_list -> {
                    supportActionBar?.title = "List"
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment2)
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_setting -> {
                    supportActionBar?.title = "Setting"
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment3)
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentById(R.id.main_frameLayout) !is SearchFragment) {
            binding.mainBottomNavigation.selectedItemId = R.id.navigation_search
        } else {
            if(doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }
}
