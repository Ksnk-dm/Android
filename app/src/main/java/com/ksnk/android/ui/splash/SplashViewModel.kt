package com.ksnk.android.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ksnk.android.R
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity
import com.ksnk.android.data.repository.RepositoryQuestion
import com.ksnk.android.data.repository.RepositoryTheme
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repositoryTheme: RepositoryTheme,
    private val repositoryQuestion: RepositoryQuestion
) : ViewModel() {

    fun insertTheme(theme: ThemeEntity) =
        viewModelScope.launch {
            repositoryTheme.insertTheme(theme)
        }

    fun insertQuestion(question: QuestionEntity) =
        viewModelScope.launch {
            repositoryQuestion.insertQuestion(question)
        }

    fun insertQuestions(list: List<QuestionEntity>) =
        viewModelScope.launch {
            repositoryQuestion.insertQuestions(list)
        }
}