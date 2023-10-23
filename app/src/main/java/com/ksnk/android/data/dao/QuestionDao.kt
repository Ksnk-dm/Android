package com.ksnk.android.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ksnk.android.data.QuestionCounts
import com.ksnk.android.data.entity.QuestionEntity

@Dao
interface QuestionDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionEntity: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<QuestionEntity>)

    @Delete
    suspend fun delete(questionEntity: QuestionEntity)

    @Update
    suspend fun update(questionEntity: QuestionEntity)

    @Query("SELECT * FROM questionEntity")
    fun getAll(): LiveData<List<QuestionEntity>>

    @Query("SELECT COUNT(*) as total, SUM(CASE WHEN isOpen = 1 THEN 1 ELSE 0 END) as openCount, SUM(CASE WHEN isComplete = 1 THEN 1 ELSE 0 END) as completeCount FROM questionEntity WHERE themeId = :themeId")
    fun getQuestionCountsForTheme(themeId: Long): LiveData<QuestionCounts>

    @Query("SELECT * FROM questionEntity WHERE isComplete = 1")
    fun getQuestionCountFrom(): LiveData<List<QuestionEntity>>

    @Query("SELECT * FROM questionEntity WHERE themeId = :themeId")
    fun getAllByTheme(themeId: Int?): LiveData<List<QuestionEntity>>
}