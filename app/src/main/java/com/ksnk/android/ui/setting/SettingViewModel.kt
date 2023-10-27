package com.ksnk.android.ui.setting

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksnk.android.data.repository.RepositoryQuestion
import com.ksnk.android.data.repository.RepositoryTheme
import kotlinx.coroutines.launch

class SettingViewModel(
    private val repositoryTheme: RepositoryTheme,
    private val repositoryQuestion: RepositoryQuestion,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun clearAll() {
        viewModelScope.launch {
            sharedPreferences.edit().clear().commit()
            repositoryTheme.deleteAllThemes()
            repositoryQuestion.deleteAllQuestions()
        }
    }
}