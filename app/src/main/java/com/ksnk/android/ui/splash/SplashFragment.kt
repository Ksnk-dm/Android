package com.ksnk.android.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ksnk.android.R
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity
import com.ksnk.android.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val viewModel by viewModel<SplashViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MESSAGE::: ", viewModel.getThemeCount().toString() + " " + viewModel.getQuestionCount().toString())

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
                    themes.add(themeEntity)
                    Log.d("MESSAGE::: 1", dataSnapshot.child("themes").children.toList().size.toString())
                    Log.d("MESSAGE::: 2", viewModel.getThemeCount().toString())
//                    if (viewModel.getThemeCount() < dataSnapshot.child("themes").children.toList().size) {
//                       // viewModel.insertTheme(themeEntity)
//                        viewModel.saveThemeCount(dataSnapshot.child("themes").children.toList().size)
//                    }


                    for (questionSnapshot in themeSnapshot.child("questions").children) {
                        val questionName = questionSnapshot.key
                        val questionData = questionSnapshot.value as Map<String, Any>
                        val questionText = questionData["question"] as String
                        val answerText = questionData["answer"] as String

                        val questionEntity = QuestionEntity(themeId = themeId?.toLong() ?: 0, question = questionText, answer = answerText)
                        questions.add(questionEntity)
                    }
                }
                if (themes.size > viewModel.getThemeCount()) viewModel.insertThemes(themes)
                if (questions.size > viewModel.getQuestionCount()) viewModel.insertQuestions(questions)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MESSAGE::: ", error.details)
            }
        })

        findNavController().navigate(R.id.action_splashFragment_to_questionFragment)
    }
}