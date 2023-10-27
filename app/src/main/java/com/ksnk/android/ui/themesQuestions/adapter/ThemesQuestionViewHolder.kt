package com.ksnk.android.ui.themesQuestions.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ksnk.android.R
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity
import com.ksnk.android.databinding.ViewPagerItemBinding
import com.ksnk.android.ui.themesQuestions.ThemesQuestionFragment
import com.ksnk.android.ui.themesQuestions.ThemesQuestionViewModel

class ThemesQuestionViewHolder(
    private val binding: ViewPagerItemBinding,
    private val viewModel: ThemesQuestionViewModel,
    private val viewPager: ViewPager2
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: QuestionEntity, position: Int, questionListSize: Int, fragment: ThemesQuestionFragment) {
        with(binding) {

            if (position + 1 == questionListSize) {
                buttonNext.text = "Повернутись"
                buttonNext.setOnClickListener {
                    findNavController(fragment).popBackStack()
                }
            }

            rlAnswer.visibility = View.GONE
            buttonAnswer.visibility = View.VISIBLE
            buttonNext.visibility = View.GONE
            if (item.isFavorite) imageButtonFavorite.setImageResource(R.drawable.baseline_favorite_24) else
                imageButtonFavorite.setImageResource(R.drawable.baseline_favorite_border_24)

            textViewQuestion.text = item.question
            val theme: ThemeEntity = viewModel.getThemeById(item.themeId)

            textViewTheme.text = theme.name
            textViewNumberTheme.text = "${position.plus(1)}. "
            textViewAnswer.text = item.answer.parseAsHtml()

            buttonAnswer.setOnClickListener {
                rlAnswer.visibility = View.VISIBLE
                buttonNext.visibility = View.VISIBLE
                item.isOpen = true
                viewModel.updateQuestion(item)
                buttonAnswer.visibility = View.GONE
            }

            buttonNext.setOnClickListener {
                item.isOpen = true
                viewModel.updateQuestion(item)
                viewPager.currentItem = viewPager.currentItem.plus(1)

                if (position + 1 == questionListSize) {
                    buttonNext.text = "Повернутись"
                    findNavController(fragment).popBackStack()
                }
            }

            imageButtonFavorite.setOnClickListener {
                if (item.isFavorite) {
                    item.isFavorite = false
                    imageButtonFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                    viewModel.updateQuestion(item)
                } else {
                    item.isFavorite = true
                    imageButtonFavorite.setImageResource(R.drawable.baseline_favorite_24)
                    viewModel.updateQuestion(item)
                }
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            viewModel: ThemesQuestionViewModel,
            viewPager: ViewPager2
        ): ThemesQuestionViewHolder {
            val binding = ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ThemesQuestionViewHolder(binding, viewModel, viewPager)
        }
    }
}