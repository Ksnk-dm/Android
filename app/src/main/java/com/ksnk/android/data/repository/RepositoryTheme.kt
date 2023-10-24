package com.ksnk.android.data.repository

import androidx.lifecycle.LiveData
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity

interface RepositoryTheme {

    suspend fun insertTheme(themeEntity: ThemeEntity)
    suspend fun insertThemes(themeEntity: List<ThemeEntity>)
    suspend fun updateTheme(themeEntity: ThemeEntity)
    suspend fun getAllThemes(): LiveData<List<ThemeEntity>>
    suspend fun getThemeById(id: Long): ThemeEntity
    suspend fun deleteTheme(themeEntity: ThemeEntity)
    suspend fun deleteAllThemes()
}