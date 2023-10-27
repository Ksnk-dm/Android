package com.ksnk.android.data.repository

import androidx.lifecycle.LiveData
import com.ksnk.android.data.entity.LibraryEntity

interface RepositoryLibrary {

    suspend fun insert(list: List<LibraryEntity>)

    suspend fun getAll(): LiveData<List<LibraryEntity>>
}