package com.ksnk.android.ui.themes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ksnk.android.data.QuestionCounts
import com.ksnk.android.data.entity.ThemeEntity
import com.ksnk.android.data.repository.RepositoryQuestion
import com.ksnk.android.data.repository.RepositoryTheme

class ThemesViewModel(
    private val repositoryTheme: RepositoryTheme,
    private val repositoryQuestion: RepositoryQuestion
) : ViewModel() {

    fun getQuestionCountForTheme(themeId: Long): LiveData<QuestionCounts> =
        repositoryQuestion.getQuestionCountForTheme(themeId)

    fun getAllThemes(): LiveData<List<ThemeEntity>> = liveData {
        emitSource(repositoryTheme.getAllThemes())
    }
}