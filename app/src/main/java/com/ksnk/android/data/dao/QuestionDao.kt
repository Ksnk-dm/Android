package com.ksnk.android.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ksnk.android.model.QuestionCounts
import com.ksnk.android.data.entity.QuestionEntity

@Dao
interface QuestionDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionEntity: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(list: List<QuestionEntity>)

    @Delete
    suspend fun delete(questionEntity: QuestionEntity)

    @Update
    suspend fun update(questionEntity: QuestionEntity)

    @Query("SELECT * FROM questionEntity")
    fun getAll(): LiveData<List<QuestionEntity>>

    @Query("DELETE FROM questionEntity")
    fun deleteAll()

    @Query("SELECT * FROM questionEntity ORDER BY RANDOM() LIMIT 20")
    fun getRandomQuestions(): List<QuestionEntity>

    @Query("SELECT COUNT(*) as total, SUM(CASE WHEN isOpen = 1 THEN 1 ELSE 0 END) as openCount, SUM(CASE WHEN isFavorite = 1 THEN 1 ELSE 0 END) as favoriteCount FROM questionEntity WHERE themeId = :themeId")
    fun getQuestionCountsForTheme(themeId: Long): LiveData<QuestionCounts>

    @Query("SELECT * FROM questionEntity WHERE isOpen = 1")
    fun getQuestionCountFrom(): LiveData<List<QuestionEntity>>

    @Query("SELECT * FROM questionEntity WHERE themeId = :themeId")
    fun getAllByTheme(themeId: Int?): List<QuestionEntity>

    @Query("SELECT * FROM questionEntity WHERE isFavorite = 1")
    fun getQuestionByFavorite(): List<QuestionEntity>
}