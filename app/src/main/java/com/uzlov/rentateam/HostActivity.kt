package com.uzlov.rentateam

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.uzlov.rentateam.databinding.ActivityMainBinding
import com.uzlov.rentateam.ui.about_app.AboutAppFragment
import com.uzlov.rentateam.ui.home.UsersFragment

class HostActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setActiveFragment(UsersFragment.newInstance())

        binding?.navView?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    setActiveFragment(UsersFragment.newInstance())
                    true
                }
                R.id.navigation_dashboard -> {
                    setActiveFragment(AboutAppFragment.newInstance())
                    true
                }
                else -> false
            }
        }

    }

    private fun setActiveFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
        }.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}