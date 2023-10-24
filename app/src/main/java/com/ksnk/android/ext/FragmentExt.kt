package com.ksnk.android.ext

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateFragment(id: Int) =
    findNavController().navigate(id)
