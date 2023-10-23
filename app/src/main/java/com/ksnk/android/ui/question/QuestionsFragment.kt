package com.ksnk.android.ui.question

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.BaseFragment
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentQuestionsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionsFragment : BaseFragment(R.layout.fragment_questions) {
    private val viewBinding by viewBinding(FragmentQuestionsBinding::bind)
    private val viewModel by viewModel<QuestionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            viewModel.getAllQuestions().observe(requireActivity(), Observer { questionList ->
                Log.d("MESSAGE::: ", questionList.size.toString())
                progressBar.max = questionList.size
                viewModel.getQuestionCountForIsOpen().observe(requireActivity(), Observer { count ->
                    progressBar.progress = count.size
                })
            })
            relativeLayoutThemes.setOnClickListener {
                findNavController().navigate(R.id.action_questionFragment_to_themesFragment)
                hideBottomNavigation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation()
    }
}