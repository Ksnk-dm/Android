package com.ksnk.android.data.repository.impl

import androidx.lifecycle.LiveData
import com.ksnk.android.data.dao.ThemeDao
import com.ksnk.android.data.entity.ThemeEntity
import com.ksnk.android.data.repository.RepositoryTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryThemeImpl(private val dao: ThemeDao) : RepositoryTheme {

    override suspend fun insertTheme(themeEntity: ThemeEntity) =
        withContext(Dispatchers.IO) { dao.insert(themeEntity) }

    override suspend fun insertThemes(themeEntity: List<ThemeEntity>) =
        withContext(Dispatchers.IO) { dao.insertList(themeEntity) }

    override suspend fun updateTheme(themeEntity: ThemeEntity) =
        withContext(Dispatchers.IO) { dao.update(themeEntity) }

    override suspend fun getAllThemes(): LiveData<List<ThemeEntity>> =
        withContext(Dispatchers.IO) { dao.getAll() }

    override suspend fun getThemeById(id: Long): ThemeEntity =
        withContext(Dispatchers.IO) {dao.getThemeById(id)}

    override suspend fun deleteTheme(themeEntity: ThemeEntity) =
        withContext(Dispatchers.IO) { dao.delete(themeEntity) }

    override suspend fun deleteAllThemes() =
        withContext(Dispatchers.IO) { dao.deleteAll() }

}