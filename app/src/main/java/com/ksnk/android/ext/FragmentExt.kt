package com.ksnk.android.ext

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateFragment(id: Int) =
    findNavController().navigate(id)

fun Fragment.navigateFragment(id: Int, bundle: Bundle) =
    findNavController().navigate(id, bundle)

fun Fragment.showToast(text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
