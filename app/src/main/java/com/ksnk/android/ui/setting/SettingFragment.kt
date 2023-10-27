package com.ksnk.android.ui.setting

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.OnCompleteListener
import com.google.android.play.core.tasks.Task
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentDialogAcceptBinding
import com.ksnk.android.databinding.FragmentSettingBinding
import com.ksnk.android.ext.navigateFragment
import com.ksnk.android.ext.showToast
import com.ksnk.android.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment(R.layout.fragment_setting) {

    private val viewBinding by viewBinding(FragmentSettingBinding::bind)
    private lateinit var viewBindingDialog: FragmentDialogAcceptBinding
    private val viewModel by viewModel<SettingViewModel>()
    private lateinit var reviewManager: ReviewManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewManager = ReviewManagerFactory.create(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation()
        with(viewBinding) {
            linearTheme.setOnClickListener {
                showToast("Зміна теми в розробці")
            }

            linearLanguage.setOnClickListener {
                showToast("Зміна мови в розробці")
            }

            linearDelete.setOnClickListener {
                showCustomAlertDialog()
            }

            materialToolbar.setNavigationOnClickListener {
                navigateFragment(R.id.action_settingFragment_to_questionFragment)
            }

            linearRate.setOnClickListener {
                requestAppReview()
            }

            linearPrivacy.setOnClickListener {
                navigateFragment(R.id.action_settingFragment_to_privacyFragment)
            }
        }
    }

    private fun showCustomAlertDialog() {
        val dialogBuilder = AlertDialog.Builder(requireActivity(), R.style.RoundedAlertDialog)
        val inflater = layoutInflater
        viewBindingDialog = FragmentDialogAcceptBinding.inflate(inflater)
        dialogBuilder.setView(viewBindingDialog.root)
        val alertDialog = dialogBuilder.create()

        viewBindingDialog.relativeLayoutContinue.setOnClickListener {
            viewModel.clearAll()
            alertDialog.dismiss()
            navigateFragment(R.id.action_settingFragment_to_splashFragment)
        }

        viewBindingDialog.relativeLayoutCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.setCancelable(false)

        alertDialog.show()
        alertDialog.window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    private fun requestAppReview() {
        val request: Task<ReviewInfo> = reviewManager.requestReviewFlow()
        request.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo: ReviewInfo = task.result

                val flow: Task<Void> = reviewManager.launchReviewFlow(requireActivity(), reviewInfo)
                flow.addOnCompleteListener { _ ->

                }
            } else showToast("Дякую за оцінку")

        })
    }
}