package com.ksnk.android.ui.themesQuestions

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentThemeQuestionsBinding
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.ui.themesQuestions.adapter.CustomTabAdapter
import com.ksnk.android.ui.themesQuestions.adapter.ThemesQuestionAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ThemesQuestionFragment : BaseFragment(R.layout.fragment_theme_questions) {

    private val viewBinding by viewBinding(FragmentThemeQuestionsBinding::bind)
    private val viewModel by viewModel<ThemesQuestionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        with(viewBinding) {

            materialToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            val themeId = arguments?.getLong(THEME_ID_KEY, 777)
            initAdapters(themeId?.toInt() ?: 777)
        }
    }

    private fun initAdapters(themeId: Int) {

        with(viewBinding) {
            val questionList = when (themeId) {
                777 -> viewModel.getRandomQuestions()
                555 -> viewModel.getQuestionByFavorite()
                else -> viewModel.getAllByTheme(themeId)
            }

            val pagerAdapter = ThemesQuestionAdapter(questionList, viewModel, viewPager, this@ThemesQuestionFragment)

            viewPager.adapter = pagerAdapter

            val customTabAdapter = CustomTabAdapter(tabLayout.context)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                val customView = customTabAdapter.createTabView(position)
                tab.customView = customView

                if (position == viewPager.currentItem) customView.setBackgroundResource(R.drawable.circular_border)
                else customView.setBackgroundResource(R.drawable.circular_border)

            }.attach()

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    val selectedTabView = tabLayout.getTabAt(position)?.customView
                    val unselectedTabViews = tabLayout.getTabAt(viewPager.currentItem)?.customView

                    selectedTabView?.let { it.setBackgroundResource(R.drawable.circular_border) }
                    unselectedTabViews?.let { customTabAdapter.deselectTabView(it) }
                }
            })
        }
    }

    companion object {
        const val THEME_ID_KEY = "themeId"
    }

}