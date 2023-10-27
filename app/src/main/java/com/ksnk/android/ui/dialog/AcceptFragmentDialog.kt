package com.ksnk.android.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ksnk.android.databinding.FragmentDialogAcceptBinding


class AcceptFragmentDialog : DialogFragment() {

    private lateinit var binding: FragmentDialogAcceptBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.relativeLayoutCancel.setOnClickListener {
            // Действие при нажатии на кнопку "Закрыть диалог"
            dismiss()
        }

        binding.relativeLayoutContinue.setOnClickListener {
            dismiss()
        }
    }
}