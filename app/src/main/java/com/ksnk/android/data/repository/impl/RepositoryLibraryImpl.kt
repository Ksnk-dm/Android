package com.ksnk.android.data.repository.impl

import androidx.lifecycle.LiveData
import com.ksnk.android.data.dao.LibraryDao
import com.ksnk.android.data.entity.LibraryEntity
import com.ksnk.android.data.repository.RepositoryLibrary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryLibraryImpl(private val dao: LibraryDao) : RepositoryLibrary {

    override suspend fun insert(list: List<LibraryEntity>) =
        withContext(Dispatchers.IO) { dao.insertList(list) }

    override suspend fun getAll(): LiveData<List<LibraryEntity>> =
        withContext(Dispatchers.IO) { dao.getAll() }
}