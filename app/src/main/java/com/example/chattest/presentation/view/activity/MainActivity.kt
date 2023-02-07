package com.example.chattest.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.chattest.R
import com.example.chattest.databinding.ActivityMainBinding
import com.example.chattest.presentation.view.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscriberUi()
    }

    private fun subscriberUi() {
        val navigationHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment

        with(binding) {
            navigationBottom.setupWithNavController(navigationHostFragment.navController)
            navigationBottom.run {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_home -> showFragment(HomeFragment(), HomeFragment.TAG)
                        R.id.navigation_people -> showFragment(PeopleFragment(), PeopleFragment.TAG)
                        R.id.navigation_message -> showFragment(MessageFragment(), MessageFragment.TAG)
                        R.id.navigation_my_page -> showFragment(MyPageFragment(), HomeFragment.TAG)
                        R.id.navigation_recruit -> showFragment(RecruitFragment(), RecruitFragment.TAG)
                    }
                    true
                }
                selectedItemId = R.id.navigation_message
            }
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_host_fragment, fragment, tag)
            .commit()
    }
}