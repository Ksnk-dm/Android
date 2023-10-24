package com.ksnk.android.ui.themesQuestions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.repository.RepositoryQuestion
import com.ksnk.android.data.repository.RepositoryTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ThemesQuestionViewModel(
    private val repositoryTheme: RepositoryTheme,
    private val repositoryQuestion: RepositoryQuestion
) : ViewModel() {

    fun getAllByTheme(themeId: Int?): List<QuestionEntity> =
         runBlocking { repositoryQuestion.getAllByTheme(themeId) }




    fun updateQuestion(question: QuestionEntity) =
        viewModelScope.launch { repositoryQuestion.updateQuestion(question) }
}