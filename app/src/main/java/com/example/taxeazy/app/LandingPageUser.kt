package com.example.taxeazy.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taxeazy.R
import com.example.taxeazy.fragments.MainPageFragment
import com.example.taxeazy.fragments.MyApplicationsFragment
import com.example.taxeazy.fragments.MyBillsFragment
import com.example.taxeazy.fragments.SearchFragment
import com.example.taxeazy.fragments.UserProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class LandingPageUser : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        replaceFragment(MainPageFragment())

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(MainPageFragment())
                R.id.bills -> replaceFragment(MyBillsFragment())
                R.id.search -> replaceFragment(SearchFragment())
                R.id.applications -> replaceFragment(MyApplicationsFragment())
                R.id.profile -> replaceFragment(UserProfileFragment())
                else -> {
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
