package com.example.taxeazy.app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taxeazy.R
import com.example.taxeazy.fragments.MainPageFragment
import com.example.taxeazy.fragments.MyApplicationsFragment
import com.example.taxeazy.fragments.MyBillsFragment
import com.example.taxeazy.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class LandingPageCa : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        replaceFragment(MainPageFragment())

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(MainPageFragment())
                R.id.search -> replaceFragment(SearchFragment())
                R.id.applications -> replaceFragment(MyApplicationsFragment())
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