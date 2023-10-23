package com.ksnk.android.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ksnk.android.BaseFragment
import com.ksnk.android.R
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val viewModel by viewModel<SplashViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().navigate(R.id.action_splashFragment_to_questionFragment)
//        val database = FirebaseDatabase.getInstance()
//        val reference = database.getReference("themes")
//
//        val newTheme = Themes(1,1,"title") // Здесь создайте объект, который вы хотите добавить в базу данных
//
//        val key = reference.push().key // Создайте уникальный ключ для новой записи
//        key?.let {
//            reference.child(it).setValue(newTheme) // Устанавливаем значение по ключу
//                .addOnSuccessListener {
//                    // Значение успешно добавлено
//                }
//                .addOnFailureListener {
//                    // Произошла ошибка при добавлении значения
//                }
//        }
//        val database = FirebaseDatabase.getInstance()
//        val reference = database.getReference("themes")
//
//        val desireList = mutableListOf<Themes>()
//        // Создаем новую тему
//        val newTheme = Themes(1, 1, "Название темы")
//
//        val key = reference.push().key // Создаем уникальный ключ для темы
//        key?.let {
//            val themeReference = reference.child(it) // Создаем ссылку на узел темы
//
//            // Теперь мы добавим вопросы внутри этой темы
//            val question1 = Question("Вопрос 1", "Ответ на вопрос 1")
//
//
//            val question2 = Question("Вопрос 2",  "Ответ на вопрос 2")
//
//            val questionsMap = mapOf(
//                "question1" to question1,
//                "question2" to question2
//            )
//
//            // Устанавливаем вопросы внутри темы
//            themeReference.child("questions").setValue(questionsMap)
//                .addOnSuccessListener {
//                    // Вопросы успешно добавлены в тему
//                }
//                .addOnFailureListener {
//                    // Произошла ошибка при добавлении вопросов
//                }
//        }
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference // Ссылка на корневой узел базы данных

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val themes = mutableListOf<ThemeEntity>()
                val questions = mutableListOf<QuestionEntity>()

                for (themeSnapshot in dataSnapshot.child("themes").children) {
                    val themeId = themeSnapshot.child("id").getValue(String::class.java)
                    val themeName = themeSnapshot.key
                    val themeEntity = ThemeEntity(themeId?.toLong() ?: 0, themeName ?: "")

                    // Сохраните тему в базу данных Room
                    viewModel.insertTheme(themeEntity)

                    for (questionSnapshot in themeSnapshot.child("questions").children) {
                        val questionName = questionSnapshot.key
                        val questionData = questionSnapshot.value as Map<String, Any>
                        val questionText = questionData["question"] as String
                        val answerText = questionData["answer"] as String

                        val questionEntity = QuestionEntity(themeId = themeId?.toLong() ?: 0, question = questionText, answer = answerText)
                        questions.add(questionEntity)
                    }
                }

                viewModel.insertQuestions(questions)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MESSAGE::: ", error.details)
            }
        })
    }
}