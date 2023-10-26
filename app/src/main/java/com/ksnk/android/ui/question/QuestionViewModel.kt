package com.ksnk.android.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.repository.RepositoryQuestion
import com.ksnk.android.data.repository.RepositoryTheme
import kotlinx.coroutines.runBlocking

class QuestionViewModel(
    private val repositoryTheme: RepositoryTheme,
    private val repositoryQuestion: RepositoryQuestion
) : ViewModel() {

    fun getQuestionCountForIsOpen(): LiveData<List<QuestionEntity>> =
        liveData { emitSource(repositoryQuestion.getQuestionCountFrom()) }

    fun getAllQuestions(): LiveData<List<QuestionEntity>> = liveData {
        emitSource(repositoryQuestion.getAllQuestions())
    }

    fun getQuestionByFavorite(): List<QuestionEntity> =
        runBlocking {
            repositoryQuestion.getQuestionByFavorite()
        }
}