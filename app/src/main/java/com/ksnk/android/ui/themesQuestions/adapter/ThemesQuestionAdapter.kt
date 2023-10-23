package com.ksnk.android.ui.themesQuestions.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.ui.themesQuestions.ThemesQuestionViewModel

class ThemesQuestionAdapter(
    private val questionList: List<QuestionEntity>,
    private val viewModel: ThemesQuestionViewModel
) : RecyclerView.Adapter<ThemesQuestionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesQuestionViewHolder =
        ThemesQuestionViewHolder.create(parent, viewModel)

    override fun getItemCount(): Int =
        questionList.size

    override fun onBindViewHolder(holder: ThemesQuestionViewHolder, position: Int) =
        holder.bind(questionList[position])
}
