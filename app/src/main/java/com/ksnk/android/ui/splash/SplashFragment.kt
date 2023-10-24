package com.ksnk.android.ui.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ksnk.android.R
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity
import com.ksnk.android.databinding.FragmentSplashBinding
import com.ksnk.android.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val viewModel by viewModel<SplashViewModel>()
    private val viewBinding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()


        viewBinding.animationView.addAnimatorUpdateListener { valueAnimator ->
            val progress = valueAnimator.animatedFraction
            val currentFrame = (progress * viewBinding.animationView.maxFrame).toInt()
            viewBinding.progressBar2.progress = currentFrame*2

            if (viewBinding.progressBar2.progress == 30) {
                viewModel.getAllQuestions().observe(requireActivity(), Observer { questionList ->
                    if (isInternetAvailable()) loadData()
                    else if (questionList.isNotEmpty()) findNavController().navigate(R.id.action_splashFragment_to_questionFragment) else
                        findNavController().navigate(R.id.action_splashFragment_to_errorFragment)
                })
            }
        }
    }

    private fun loadData() {
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val themes = mutableListOf<ThemeEntity>()
                val questions = mutableListOf<QuestionEntity>()

                for (themeSnapshot in dataSnapshot.child(THEMES_PATH).children) {
                    val themeId = themeSnapshot.child(ID_PATH).getValue(String::class.java)
                    val themeName = themeSnapshot.key

                    themes.add(
                        ThemeEntity(
                            themeId?.toLong() ?: 0,
                            themeName ?: ""
                        )
                    )

                    for (questionSnapshot in themeSnapshot.child(QUESTIONS_PATH).children) {
                        val questionData = questionSnapshot.value as Map<String, Any>
                        val questionText = questionData[QUESTION_KEY] as String
                        val answerText = questionData[ANSWER_KEY] as String

                        questions.add(
                            QuestionEntity(
                                themeId = themeId?.toLong() ?: 0,
                                question = questionText,
                                answer = answerText
                            )
                        )
                    }
                }

                if (themes.size > viewModel.getThemeCount()) viewModel.insertThemes(themes)
                if (questions.size > viewModel.getQuestionCount()) viewModel.insertQuestions(questions)

                runCatching {
                    findNavController().navigate(R.id.action_splashFragment_to_questionFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                findNavController().navigate(R.id.action_splashFragment_to_errorFragment)
            }
        })
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    companion object {
        const val THEMES_PATH = "themes"
        const val ID_PATH = "id"
        const val QUESTIONS_PATH = "questions"

        const val QUESTION_KEY = "question"
        const val ANSWER_KEY = "answer"

        const val THEME_COUNT_KEY = "theme_count"
        const val QUESTION_COUNT_KEY = "question_count"
    }
}