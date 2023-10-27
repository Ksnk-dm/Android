package com.ksnk.android.ui.question

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentQuestionsBinding
import com.ksnk.android.ext.navigateFragment
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.ui.themesQuestions.ThemesQuestionFragment
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionsFragment : BaseFragment(R.layout.fragment_questions) {

    private val viewBinding by viewBinding(FragmentQuestionsBinding::bind)
    private val viewModel by viewModel<QuestionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomNavigation()

        with(viewBinding) {
            viewModel.getAllQuestions().observe(this@QuestionsFragment, Observer { questionList ->
                progressBar.max = questionList.size
                viewModel.getQuestionCountForIsOpen().observe(this@QuestionsFragment, Observer { count ->
                    progressBar.progress = count.size
                })
            })

            textViewFavoriteCount.text = viewModel.getQuestionByFavorite().size.toString()

            relativeLayoutThemes.setOnClickListener {
                navigateFragment(R.id.action_questionFragment_to_themesFragment)
            }

            relativeLayoutRandom.setOnClickListener {
                navigateFragment(R.id.action_questionFragment_to_questionThemeFragment)
            }

            relativeLayoutFavorite.setOnClickListener {
                val bundle = bundleOf(ThemesQuestionFragment.THEME_ID_KEY to 555L)
                navigateFragment(R.id.action_questionFragment_to_questionThemeFragment, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation()
    }
}