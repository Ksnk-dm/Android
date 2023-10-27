package com.ksnk.android.data.repository

import androidx.lifecycle.LiveData
import com.ksnk.android.data.dao.QuestionDao
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.model.QuestionCounts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryQuestionImpl(private val dao: QuestionDao) : RepositoryQuestion {

    override suspend fun insertQuestion(questionEntity: QuestionEntity) =
        withContext(Dispatchers.IO) { dao.insert(questionEntity) }

    override suspend fun insertQuestions(list: List<QuestionEntity>) =
        withContext(Dispatchers.IO) { dao.insertList(list) }

    override suspend fun updateQuestion(questionEntity: QuestionEntity) =
        withContext(Dispatchers.IO) { dao.update(questionEntity) }

    override suspend fun getAllQuestions(): LiveData<List<QuestionEntity>> =
        withContext(Dispatchers.IO) { dao.getAll() }

    override suspend fun getQuestionByFavorite(): List<QuestionEntity> =
        withContext(Dispatchers.IO) { dao.getQuestionByFavorite() }

    override suspend fun deleteQuestion(questionEntity: QuestionEntity) =
        withContext(Dispatchers.IO) { dao.delete(questionEntity) }

    override fun getQuestionCountForTheme(themeId: Long): LiveData<QuestionCounts> =
        dao.getQuestionCountsForTheme(themeId)

    override suspend fun getQuestionCountFrom(): LiveData<List<QuestionEntity>> =
        withContext(Dispatchers.IO) { dao.getQuestionCountFrom() }

    override suspend fun getAllByTheme(themeId: Int?): List<QuestionEntity> =
        withContext(Dispatchers.IO) { dao.getAllByTheme(themeId) }

    override suspend fun getRandomQuestions(): List<QuestionEntity> =
        withContext(Dispatchers.IO) { dao.getRandomQuestions() }

    override suspend fun deleteAllQuestions() =
        withContext(Dispatchers.IO) { dao.deleteAll() }
}