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

    @Query("SELECT * FROM themeEntity WHERE themeId = :themeId")
    suspend fun getThemeById(themeId: Long): ThemeEntity

    @Query("DELETE FROM themeEntity")
    fun deleteAll()

    @Delete
    suspend fun delete(themeEntity: ThemeEntity)

    @Update
    suspend fun update(themeEntity: ThemeEntity)

    @Query("SELECT * FROM themeEntity ORDER BY themeId ASC")
    fun getAll(): LiveData<List<ThemeEntity>>
}