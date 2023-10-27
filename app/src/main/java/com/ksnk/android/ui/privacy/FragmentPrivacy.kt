package com.ksnk.android.ui.privacy

import android.os.Bundle
import android.view.View
import androidx.core.text.parseAsHtml
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentPrivacyBinding
import com.ksnk.android.ext.navigateFragment
import com.ksnk.android.ui.base.BaseFragment

class FragmentPrivacy : BaseFragment(R.layout.fragment_privacy) {

    private val viewBinding by viewBinding(FragmentPrivacyBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        with(viewBinding) {
            materialToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            textViewPrivacy.text = getString(R.string.privacy_policy).parseAsHtml()
        }

    }
}