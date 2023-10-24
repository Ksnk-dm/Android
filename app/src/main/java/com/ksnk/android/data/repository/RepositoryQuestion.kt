package com.ksnk.android.data.repository

import androidx.lifecycle.LiveData
import com.ksnk.android.data.QuestionCounts
import com.ksnk.android.data.entity.QuestionEntity

interface RepositoryQuestion {

    suspend fun insertQuestion(questionEntity: QuestionEntity)
    suspend fun insertQuestions(list: List<QuestionEntity>)
    suspend fun updateQuestion(questionEntity: QuestionEntity)
    suspend fun getAllQuestions(): LiveData<List<QuestionEntity>>
    suspend fun deleteQuestion(questionEntity: QuestionEntity)
    suspend fun deleteAllQuestions()
    fun getQuestionCountForTheme(themeId: Long): LiveData<QuestionCounts>
    suspend fun getQuestionCountFrom(): LiveData<List<QuestionEntity>>
    suspend fun getAllByTheme(themeId: Int?): List<QuestionEntity>
}