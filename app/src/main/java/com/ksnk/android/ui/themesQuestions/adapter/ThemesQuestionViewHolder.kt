package com.ksnk.android.ui.themesQuestions.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.databinding.ViewPagerItemBinding
import com.ksnk.android.ui.themesQuestions.ThemesQuestionViewModel

class ThemesQuestionViewHolder(private val binding: ViewPagerItemBinding, private val viewModel: ThemesQuestionViewModel) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: QuestionEntity) {
        binding.textViewQuestion.text = item.question
        binding.textViewAnswer.text = item.answer.parseAsHtml()
        binding.buttonAnswer.setOnClickListener {
            binding.rlAnswer.visibility = View.VISIBLE
            item.isOpen = true
            viewModel.updateQuestion(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup,viewModel: ThemesQuestionViewModel): ThemesQuestionViewHolder {
            val binding = ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ThemesQuestionViewHolder(binding, viewModel)
        }
    }
}