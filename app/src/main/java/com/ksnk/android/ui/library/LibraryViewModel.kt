package com.ksnk.android.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ksnk.android.data.entity.LibraryEntity
import com.ksnk.android.data.repository.RepositoryLibrary

class LibraryViewModel(private val repository: RepositoryLibrary) : ViewModel() {

    fun getAll(): LiveData<List<LibraryEntity>> =
        liveData { emitSource(repository.getAll()) }
}