package com.ksnk.android.ui.splash

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
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

    private fun saveThemeCount(count: Int) =
        sharedPreferences.edit().putInt(SplashFragment.THEME_COUNT_KEY, count).apply()

    fun getThemeCount(): Int =
        sharedPreferences.getInt(SplashFragment.THEME_COUNT_KEY, 0)

    private fun saveQuestionCount(count: Int) =
        sharedPreferences.edit().putInt(SplashFragment.QUESTION_COUNT_KEY, count).apply()

    fun getQuestionCount(): Int =
        sharedPreferences.getInt(SplashFragment.QUESTION_COUNT_KEY, 0)

    fun insertThemes(themes: List<ThemeEntity>) =
        viewModelScope.launch {
            saveThemeCount(themes.size)
            repositoryTheme.insertThemes(themes)
        }

    fun insertQuestions(list: List<QuestionEntity>) =
        viewModelScope.launch {
            saveQuestionCount(list.size)
            repositoryQuestion.insertQuestions(list)
        }

    fun getAllQuestions(): LiveData<List<QuestionEntity>> = liveData {
        emitSource(repositoryQuestion.getAllQuestions())
    }
}