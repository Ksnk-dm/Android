package com.ksnk.android

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes layout: Int
) : Fragment(layout) {

    private var bottomNavigationListener: BottomNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationListener) {
            bottomNavigationListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        bottomNavigationListener = null
    }

    fun hideBottomNavigation() {
        bottomNavigationListener?.hideBottomNavigationView()
    }

    fun showBottomNavigation() {
        bottomNavigationListener?.showBottomNavigationView()
    }
}