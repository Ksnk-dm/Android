package com.ksnk.android.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ksnk.android.data.entity.ThemeEntity

@Dao
interface ThemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(themeEntity: ThemeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(themes: List<ThemeEntity>)

    @Delete
    suspend fun delete(themeEntity: ThemeEntity)

    @Update
    suspend fun update(themeEntity: ThemeEntity)

    @Query("SELECT * FROM themeEntity")
    fun getAll(): LiveData<List<ThemeEntity>>
}