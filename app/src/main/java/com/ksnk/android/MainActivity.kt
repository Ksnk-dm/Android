package com.ksnk.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.databinding.ActivityMainBinding
import com.ksnk.android.databinding.FragmentQuestionsBinding


class MainActivity : AppCompatActivity(), BottomNavigationListener {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun hideBottomNavigationView() {
//        val layoutParams = viewBinding.fragmentContainerView.layoutParams as ConstraintLayout.LayoutParams
//        layoutParams.bottomToTop = -1  // -1 означает, что это ограничение должно быть убрано
//        viewBinding.fragmentContainerView.layoutParams = layoutParams
        viewBinding.bottomNavigationView.visibility = View.GONE
    }

    override fun showBottomNavigationView() {
        viewBinding.bottomNavigationView.visibility = View.VISIBLE
    }
}