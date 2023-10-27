package com.ksnk.android.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.ActivityMainBinding
import com.ksnk.android.listeners.BottomNavigationListener
import com.ksnk.android.ui.base.BaseActivity


class MainActivity : BaseActivity(R.layout.activity_main), BottomNavigationListener {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController(R.id.fragmentContainerView)
        viewBinding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        // super.onBackPressed()
    }

    override fun hideBottomNavigationView() {
          viewBinding.bottomNavigationView.visibility = View.GONE
    }

    //
    override fun showBottomNavigationView() {
          viewBinding.bottomNavigationView.visibility = View.VISIBLE
    }
}