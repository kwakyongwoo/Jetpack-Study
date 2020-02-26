package com.example.jetpackstudy.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackstudy.R
import com.example.jetpackstudy.databinding.ActivityMainBinding
import com.example.jetpackstudy.main.list.ListFragment
import com.example.jetpackstudy.main.search.SearchFragment
import com.example.jetpackstudy.main.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val fragment1 = SearchFragment()
        val fragment2 = ListFragment()
        val fragment3 = SettingFragment()

        supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment1).commitAllowingStateLoss()

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_search -> {
                    supportActionBar?.title = "검색"
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment1)
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_list -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment2)
                        .commitAllowingStateLoss()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_setting -> {
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
}
