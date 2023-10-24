package com.ksnk.android.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.listeners.BottomNavigationListener
import com.ksnk.android.R
import com.ksnk.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BottomNavigationListener {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding.bottomNavigationView.selectedItemId = R.id.questionsItem
    }

    override fun onBackPressed() {
       // super.onBackPressed()
    }

    override fun hideBottomNavigationView() {
        viewBinding.bottomNavigationView.visibility = View.GONE
    }

    override fun showBottomNavigationView() {
        viewBinding.bottomNavigationView.visibility = View.VISIBLE
    }
}