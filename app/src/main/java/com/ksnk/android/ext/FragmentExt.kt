package com.ksnk.android.ext

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateFragment(id: Int) =
    findNavController().navigate(id)

fun Fragment.navigateFragment(id: Int, bundle: Bundle) =
    findNavController().navigate(id, bundle)
