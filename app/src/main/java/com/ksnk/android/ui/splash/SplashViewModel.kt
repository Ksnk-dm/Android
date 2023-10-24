package com.ksnk.android.ui.splash

import android.content.SharedPreferences
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
    private val repositoryQuestion: RepositoryQuestion,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun saveThemeCount(count: Int) =
        sharedPreferences.edit().putInt("theme_count", count).apply()

    fun getThemeCount(): Int =
        sharedPreferences.getInt("theme_count", 0)

    private fun saveQuestionCount(count: Int) =
        sharedPreferences.edit().putInt("question_count", count).apply()

    fun getQuestionCount(): Int =
        sharedPreferences.getInt("question_count", 0)

    fun insertThemes(themes: List<ThemeEntity>) =
        viewModelScope.launch {
            saveThemeCount(themes.size)
            repositoryTheme.insertThemes(themes)
        }

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
            saveQuestionCount(list.size)
            repositoryQuestion.insertQuestions(list)
        }
}