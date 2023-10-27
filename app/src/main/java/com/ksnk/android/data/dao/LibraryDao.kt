package com.ksnk.android.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ksnk.android.data.entity.LibraryEntity

@Dao
interface LibraryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(list: List<LibraryEntity>)

    @Query("SELECT * FROM libraryEntity")
    fun getAll(): LiveData<List<LibraryEntity>>
}