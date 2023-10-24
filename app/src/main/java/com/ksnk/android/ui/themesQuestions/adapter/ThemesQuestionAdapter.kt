package com.ksnk.android.ui.themesQuestions.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.ui.themesQuestions.ThemesQuestionViewModel

class ThemesQuestionAdapter(
    private val questionList: List<QuestionEntity>,
    private val viewModel: ThemesQuestionViewModel,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<ThemesQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesQuestionViewHolder =
        ThemesQuestionViewHolder.create(parent, viewModel, viewPager2)

    override fun getItemCount(): Int =
        questionList.size

    override fun onBindViewHolder(holder: ThemesQuestionViewHolder, position: Int) =
        holder.bind(questionList[position], position)
}
