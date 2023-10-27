package com.ksnk.android.ui.setting

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentDialogAcceptBinding
import com.ksnk.android.databinding.FragmentSettingBinding
import com.ksnk.android.ext.navigateFragment
import com.ksnk.android.ext.showToast
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.ui.question.QuestionViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment(R.layout.fragment_setting) {

    private val viewBinding by viewBinding(FragmentSettingBinding::bind)
    private lateinit var viewBindingDialog: FragmentDialogAcceptBinding
    private val viewModel by viewModel<SettingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                findNavController().navigateUp()
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
}