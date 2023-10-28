package com.ksnk.android.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.ads.MobileAds
import com.ksnk.android.R
import com.ksnk.android.databinding.ActivityMainBinding
import com.ksnk.android.listeners.BottomNavigationListener
import com.ksnk.android.ui.base.BaseActivity


class MainActivity : BaseActivity(R.layout.activity_main), BottomNavigationListener {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val bottomNavBar by lazy {
        viewBinding.bottomNavigationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavBar.setupWithNavController(navController)

        bottomNavBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.questionFragment -> navController.navigate(R.id.questionFragment)
                R.id.settingFragment -> navController.navigate(R.id.settingFragment)
                R.id.libraryFragment -> navController.navigate(R.id.libraryFragment)
            }
            true
        }

        MobileAds.initialize(this) {}
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        // super.onBackPressed()
    }

    override fun hideBottomNavigationView() {
          bottomNavBar.visibility = View.GONE
    }

    //
    override fun showBottomNavigationView() {
          bottomNavBar.visibility = View.VISIBLE
    }
}