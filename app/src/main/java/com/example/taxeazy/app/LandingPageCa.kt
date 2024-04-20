package com.example.taxeazy.app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taxeazy.R
import com.example.taxeazy.fragments.CaHomeFragment
import com.example.taxeazy.fragments.MainPageFragment
import com.example.taxeazy.fragments.MyClientsFragment
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
                R.id.homeca -> replaceFragment(CaHomeFragment())
                R.id.applicationsid -> replaceFragment(MyClientsFragment())
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