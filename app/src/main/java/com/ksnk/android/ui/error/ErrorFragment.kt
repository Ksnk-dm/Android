package com.ksnk.android.ui.error

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentErrorBinding
import com.ksnk.android.ui.base.BaseFragment

class ErrorFragment : BaseFragment(R.layout.fragment_error) {

    private val viewBinding by viewBinding(FragmentErrorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()

        with(viewBinding){
            rvReturn.setOnClickListener {
                findNavController().navigate(R.id.action_errorFragment_to_splashFragment)
            }
        }
    }
}